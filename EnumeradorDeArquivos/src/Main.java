public class Main {

	public static void main(String[] args) {
		
		String path = "C:\\Users\\DavidAlain\\Desktop\\M�sicas";
		FileRenameEnumerator fre = new FileRenameEnumerator(3); //3 d�gitos
		
		System.out.println("Iniciando...");
		fre.enumerateRecursive(path, true);
		System.out.println("Terminou!");
	}
	
}
