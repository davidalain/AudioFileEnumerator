import java.io.File;
import java.security.InvalidParameterException;
import java.util.Arrays;

public class FileRenameEnumerator {

	private final String SEPARATOR_PATTERN = " - ";
	
	private int numDigits;
	private int fileEnum;

	/**
	 * 
	 * @param startValue
	 * @param numDigits
	 */
	public FileRenameEnumerator(int numDigits) {
		super();
		this.numDigits = numDigits;
		this.fileEnum = 1; //come�ar de 1 e n�o de 0
	}

	public void enumerateRecursive(String path, boolean replaceOldEnum){
		this.fileEnum = 1; //come�ar de 1 e n�o de 0
		enumerateRecursiveInternal(new File(path), replaceOldEnum);
	}

	/**
	 * 
	 * @param dir
	 */
	private void enumerateRecursiveInternal(File dir, boolean replaceOldEnum){

		if(!dir.isDirectory())
			throw new InvalidParameterException("Caminho: '" + dir.getAbsolutePath() +"' n�o � um diret�rio");

		File[] files = dir.listFiles();
		Arrays.sort(files, new ComparatorFileType());

		for(int fileIndex = 0 ; fileIndex < files.length ; fileIndex++){

			File currentFile = files[fileIndex];

			/** Os arquivos devem ser tratados primeiro e s� depois as pastas, se n�o a numera��o ficar� errada **/
			
			//Modifica o nome dos arquivos e n�o das pastas
			if(currentFile.isFile()){

				String oldAbsoluteFilePath = currentFile.getAbsolutePath();
				String oldAbsoluteDirPath = oldAbsoluteFilePath.substring(0, oldAbsoluteFilePath.lastIndexOf("\\"));
				String oldFileName = currentFile.getName();
				String fileNameTmp = oldFileName;

				//S� enumera os nomes dos arquivos de audio
				if(!AudioFileTypes.checkSupportedExtensions(oldFileName))
					continue;

				if(replaceOldEnum){

					//Descobre onde come�a o nome do arquivo, ap�s o padr�o para poder mudar a numera��o
					int patternIndex = oldFileName.indexOf(SEPARATOR_PATTERN);

					//Checa se tem o padr�o de numera��o
					if(patternIndex >= 0){

						int fileNameBeginIndex = patternIndex + SEPARATOR_PATTERN.length();
						String oldNumberStr = oldFileName.substring(0, patternIndex);
						try{
							Integer.parseInt(oldNumberStr);
							//Se chegou aqui e n�o lan�ou exce��o, ent�o conseguiu parsear o n�mero

							//Pega o �ndice onde come�aria o nome do arquivo
							fileNameTmp = oldFileName.substring(fileNameBeginIndex);
						}catch(Exception e){ } //Usado apenas para parsear o n�mero 
					}
				}

				final String format = "%0"+numDigits+"d"; //exemplo: "%03d" , para enumera��o de 000 at� 999 
				String fileEnumStr = String.format(format, fileEnum++); //cria a string com a enumera��o no formato

				String newFileName = fileEnumStr + " - " + fileNameTmp; //padr�o: "<num> - <filename>" | exemplo: "001 - musica.mp3"
				String newAbsoluteFilePath = oldAbsoluteDirPath + "\\" + newFileName;
				File newFile = new File(newAbsoluteFilePath);
				
				if(!currentFile.renameTo(newFile)){ //Renomea o arquivo
					throw new RuntimeException("Erro ao renomear arquivo de '"+ oldAbsoluteFilePath +"' para '" + newAbsoluteFilePath + "'"); 
				}

			}else if(currentFile.isDirectory()){
				//� pasta, aplica as mesmas regras

				enumerateRecursiveInternal(currentFile, replaceOldEnum);
			}

		}//fim do for

	}//fim do m�todo


}
