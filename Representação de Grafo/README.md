
  <div class="description user_content "><h2>Descrição da tarefa</h2>
<p>Nessa tarefa iremos explorar a implementação de estruturas de dados para representação de grafos e seu uso para obtenção de informações sobre grau e vizinhança dos vértices.</p>
<p>Portanto, você deve implementar (na linguagem de sua preferência) um programa que receba duas informações do usuário: (i) o nome do arquivo contendo as informações/dados sobre um <span style="text-decoration: underline;">grafo direcionado</span>; e (ii) o número de um dos vértices do grafo descrito no arquivo.</p>
<p>Seu programa deverá ler o conteúdo do arquivo e representar o grafo direcionado em memória utilizando uma das estruturas discutidas em nossas aulas. Depois disso, sua implementação deve utilizar a estrutura escolhida para produzir as seguintes informações para o vértice informado pelo usuário: (i) grau de saída; (ii) grau de entrada; (iii) conjunto de sucessores; e (iv) conjunto de predecessores. OBS.: É necessário produzir tais informações apenas para o vértice informado.</p>
<p>A escolha da estrutura faz parte da tarefa e deverá ser feita levando-se em conta o tipo do grafo (que é direcionado), o tamanho do grafo (que poderá ter centenas de milhares de vértices e dezenas de milhões de arestas) e as operações necessárias que serão realizadas sobre o mesmo (para se obter/calcular tanto o grau quanto a vizinhança de um vértice).</p>
<p>Para testar seu programa você pode utilizar os arquivos abaixo:</p>
<p><a id="7941089" class="instructure_file_link instructure_scribd_file inline_disabled" title="Link" href="/courses/143464/files/7941089?wrap=1" target="_blank" data-canvas-previewable="true">graph-test-100.txt</a></p>
<p><a id="7941088" class="instructure_file_link instructure_scribd_file inline_disabled" title="Link" href="/courses/143464/files/7941088?wrap=1" target="_blank" data-canvas-previewable="true">graph-test-50000.txt</a></p>
<p>&nbsp;</p>
<h3>Formato do arquivo contendo os dados do grafo</h3>
<p>Seu programa deverá ler as informações sobre o grafo a partir de um arquivo texto. A primeira linha desse arquivo contém o número <strong><em>n</em></strong> de vértices seguido do número <strong><em>m</em></strong> de arestas. Você deve considerar que os vértices são numerados (rotulados) de 1 a <strong><em>n</em></strong>. Depois disso, o arquivo contém uma lista com as <em><strong>m</strong></em> arestas (sendo uma aresta por linha) em que cada aresta é representada pelos seus vértices de origem e de destino.</p>
<p>Abaixo, você pode observa um esquema que representa a estrutura que deve ser esperada do arquivo:</p>
<table style="border-collapse: collapse; width: 67.7108%;" border="1">
<tbody>
<tr>
<td style="width: 50.9449%;"><em><strong>&nbsp; n</strong></em></td>
<td style="width: 49.0293%;"><em><strong>&nbsp; m</strong></em></td>
</tr>
<tr>
<td style="width: 50.9449%;">&nbsp;&lt;Origem da Aresta 1&gt;</td>
<td style="width: 49.0293%;">&nbsp;&lt;Destino da Aresta 1&gt;</td>
</tr>
<tr>
<td style="width: 50.9449%;">&nbsp;&lt;Origem da Aresta 2&gt;</td>
<td style="width: 49.0293%;">&nbsp;&lt;Destino da Aresta 2&gt;</td>
</tr>
<tr>
<td style="width: 50.9449%;">... </td>
<td style="width: 49.0293%;">... </td>
</tr>
<tr>
<td style="width: 50.9449%;">&nbsp;&lt;Origem da Aresta <em><strong>m</strong></em>&gt;</td>
<td style="width: 49.0293%;">&nbsp;&lt;Destino da Aresta <em><strong>m&gt;</strong></em></td>
</tr>
</tbody>
</table>
<p>&nbsp;</p></div>


  
  </div>
</div>
