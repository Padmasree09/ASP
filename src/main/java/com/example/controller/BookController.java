package com.example.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dao.BookDAO;
import com.example.model.Book;

@WebServlet("/books")
public class BookController extends HttpServlet {
    private BookDAO bookDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        bookDAO = new BookDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Book> books = bookDAO.getAllBooks();
        request.setAttribute("books", books);
        request.getRequestDispatcher("/books.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "add":
                    String title = request.getParameter("title");
                    String author = request.getParameter("author");
                    double price = Double.parseDouble(request.getParameter("price"));
                    Book newBook = new Book(0, title, author, price);
                    bookDAO.addBook(newBook);
                    break;
                case "delete":
                    int bookId = Integer.parseInt(request.getParameter("id"));
                    bookDAO.deleteBook(bookId);
                    break;
            }
        }
        response.sendRedirect(request.getContextPath() + "/books");
    }
}
