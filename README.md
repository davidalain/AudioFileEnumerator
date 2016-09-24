# AudioFileEnumerator

Projeto em Java de um programa que renomeia os arquivos de audio enumerando-os.
Este projeto foi criado para solucionar o problema do uso de arquivos de audio em reprodutores de MP3 com transmissor FM para carros que só conseguem ler as músicas mas não as mostra de maneira organizada.

É feita, de maneira recursiva, uma varredura total de uma dada pasta e são renomeados os arquivos de audio e são colocados um número a frente do antigo nome do arquivo para fácil identificação posterior.

Pelo fato de o display do reprodutor de MP3 não mostrar o nome da música e sim um número de ordem que é criado por ele mesmo, é necessário fazer a troca dos nomes do arquivos para identificar o arquivo em relação à ordem criada pelo reprodutor.

Por exemplo, o arquivo "musica.mp3" será renomeado para "001 - musica.mp3" e o número 001 que está no começo do nome do arquivo é o mesmo que é mostrado no display do reprodutor de MP3, assim o usuário consegue saber qual música estará escolhendo para tocar já que conhece sua ordem de enumeração.

Este programa também tem a capacidade de refazer a enumeração já feita anteriormente de acordo com qualquer modificação (inserção, deleção ou mudança de nome) de arquivos ou pastas na pasta raiz informada.

![alt tag] (https://raw.githubusercontent.com/davidalain/AudioFileEnumerator/master/P_20160923_191841.jpg) 
