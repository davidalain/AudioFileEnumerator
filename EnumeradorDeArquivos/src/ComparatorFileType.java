import java.io.File;
import java.util.Comparator;

public class ComparatorFileType implements Comparator<File>{

	@Override
	public int compare(File o1, File o2) {

		int o1Value = 0;
		int o2Value = 0;

		o1Value += o1.isFile() ? 1000 : 0;
		o2Value += o2.isFile() ? 1000 : 0;
		if(o1Value > o2Value) return -1; 	//o1 � arquivo, o2 � diret�rio
		if(o1Value < o2Value) return 1; 	//o1 � diret�rio, o2 � arquivo
		if((o1Value == 0) && (o2Value == 0)) return 0; //ambos s�o diret�rios
		
		o1Value += AudioFileTypes.checkSupportedExtensions(o1.getName()) ? 100 : 0;
		o2Value += AudioFileTypes.checkSupportedExtensions(o2.getName()) ? 100 : 0;
		if(o1Value > o2Value) return -1; 	//o1 � arquivo de audio, o2 n�o �
		if(o1Value < o2Value) return 1;		//o1 n�o � arquivo de audio, o2 �
		return 0;
		
	}



}
