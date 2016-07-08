
public class AudioFileTypes {

	private static final String[] SUPPORTED_EXTENSIONS = {".mp3", ".wma"};
	
	public static boolean checkSupportedExtensions(String filename){
		
		for(int i = 0 ; i < SUPPORTED_EXTENSIONS.length ; i++){
			if(filename.toLowerCase().endsWith(SUPPORTED_EXTENSIONS[i]))
				return true;
		}
		
		return false;
	}
	
}
