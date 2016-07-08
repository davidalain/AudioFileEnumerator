public class Main {

	public static void main(String[] args) {
		
		String path = "C:\\Users\\DavidAlain\\Desktop\\Músicas";
		FileRenameEnumerator fre = new FileRenameEnumerator(3); //3 dígitos
		
		System.out.println("Iniciando...");
		fre.enumerateRecursive(path, true);
		System.out.println("Terminou!");
	}
	
}
