package web;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/** @author isaquesv */
@WebServlet(name = "RandomServlet", urlPatterns = {"/random.html"})
public class RandomServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            int[] arrayValoresTabela = new int[6];
            int valorAtual;
            boolean valorDuplicado;
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Exercício 3 - RandomServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<a href='index.html'>Voltar</a><hr>");
            out.println("<table border='1'>");
            out.println("<tr>");
            out.println("<td>Index</td><td>Número</td>");
            out.println("</tr>");
            
            for (int i = 0; i < 6; i++) {
                do {
                    valorAtual = (int)(Math.random() * 60) + 1; // Gerando um valor entre 1 e 60
                    valorDuplicado = false;

                    for (int j = 0; j < i; j++) {
                        // Verifica se o valor gerado já existe no array
                        if (arrayValoresTabela[j] == valorAtual) {
                            valorDuplicado = true;
                            break;
                        }
                    }
                }
                // Repete o laço enquanto o último valor gerado já estiver no array
                while (valorDuplicado);

                // Adiciona o valor único ao array
                arrayValoresTabela[i] = valorAtual;
                out.println("<tr>");
                out.println("<td>" + i + "</td>");
                out.println("<td>" + arrayValoresTabela[i] + "</td>");
                out.println("</tr>");
            }
            
            // Restante da página HTML
            out.println("</table><hr>");
            out.println("<a href='me.html'><button>MeServlet</button></a>");
            out.println("<a href='greeting.html'><button>GreetingServlet</button></a>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
