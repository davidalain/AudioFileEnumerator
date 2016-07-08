import java.io.File;
import java.util.Comparator;

public class ComparatorFileType implements Comparator<File>{

	@Override
	public int compare(File o1, File o2) {

		int o1Value = 0;
		int o2Value = 0;

		o1Value += o1.isFile() ? 1000 : 0;
		o2Value += o2.isFile() ? 1000 : 0;
		if(o1Value > o2Value) return -1; 	//o1 é arquivo, o2 é diretório
		if(o1Value < o2Value) return 1; 	//o1 é diretório, o2 é arquivo
		if((o1Value == 0) && (o2Value == 0)) return 0; //ambos são diretórios
		
		o1Value += AudioFileTypes.checkSupportedExtensions(o1.getName()) ? 100 : 0;
		o2Value += AudioFileTypes.checkSupportedExtensions(o2.getName()) ? 100 : 0;
		if(o1Value > o2Value) return -1; 	//o1 é arquivo de audio, o2 não é
		if(o1Value < o2Value) return 1;		//o1 não é arquivo de audio, o2 é
		return 0;
		
	}



}
