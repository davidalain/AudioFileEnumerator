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
		this.fileEnum = 1; //começar de 1 e não de 0
	}

	public void enumerateRecursive(String path, boolean replaceOldEnum){
		this.fileEnum = 1; //começar de 1 e não de 0
		enumerateRecursiveInternal(new File(path), replaceOldEnum);
	}

	/**
	 * 
	 * @param dir
	 */
	private void enumerateRecursiveInternal(File dir, boolean replaceOldEnum){

		if(!dir.isDirectory())
			throw new InvalidParameterException("Caminho: '" + dir.getAbsolutePath() +"' não é um diretório");

		File[] files = dir.listFiles();
		Arrays.sort(files, new ComparatorFileType());

		for(int fileIndex = 0 ; fileIndex < files.length ; fileIndex++){

			File currentFile = files[fileIndex];

			/** Os arquivos devem ser tratados primeiro e só depois as pastas, se não a numeração ficará errada **/
			
			//Modifica o nome dos arquivos e não das pastas
			if(currentFile.isFile()){

				String oldAbsoluteFilePath = currentFile.getAbsolutePath();
				String oldAbsoluteDirPath = oldAbsoluteFilePath.substring(0, oldAbsoluteFilePath.lastIndexOf("\\"));
				String oldFileName = currentFile.getName();
				String fileNameTmp = oldFileName;

				//Só enumera os nomes dos arquivos de audio
				if(!AudioFileTypes.checkSupportedExtensions(oldFileName))
					continue;

				if(replaceOldEnum){

					//Descobre onde começa o nome do arquivo, após o padrão para poder mudar a numeração
					int patternIndex = oldFileName.indexOf(SEPARATOR_PATTERN);

					//Checa se tem o padrão de numeração
					if(patternIndex >= 0){

						int fileNameBeginIndex = patternIndex + SEPARATOR_PATTERN.length();
						String oldNumberStr = oldFileName.substring(0, patternIndex);
						try{
							Integer.parseInt(oldNumberStr);
							//Se chegou aqui e não lançou exceção, então conseguiu parsear o número

							//Pega o índice onde começaria o nome do arquivo
							fileNameTmp = oldFileName.substring(fileNameBeginIndex);
						}catch(Exception e){ } //Usado apenas para parsear o número 
					}
				}

				final String format = "%0"+numDigits+"d"; //exemplo: "%03d" , para enumeração de 000 até 999 
				String fileEnumStr = String.format(format, fileEnum++); //cria a string com a enumeração no formato

				String newFileName = fileEnumStr + " - " + fileNameTmp; //padrão: "<num> - <filename>" | exemplo: "001 - musica.mp3"
				String newAbsoluteFilePath = oldAbsoluteDirPath + "\\" + newFileName;
				File newFile = new File(newAbsoluteFilePath);
				
				if(!currentFile.renameTo(newFile)){ //Renomea o arquivo
					throw new RuntimeException("Erro ao renomear arquivo de '"+ oldAbsoluteFilePath +"' para '" + newAbsoluteFilePath + "'"); 
				}

			}else if(currentFile.isDirectory()){
				//é pasta, aplica as mesmas regras

				enumerateRecursiveInternal(currentFile, replaceOldEnum);
			}

		}//fim do for

	}//fim do método


}
