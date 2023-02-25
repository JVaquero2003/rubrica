package com.example.rubrica.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.rubrica.Repository.CommentRepository;
import com.example.rubrica.Repository.TicketRepository;
import com.example.rubrica.model.Comment;
import com.example.rubrica.model.Ticket;

@Service
public class TicketService {

    @Autowired
    private TicketRepository<Ticket> ticketRepository;

    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    public Ticket findTicket(long id) {
        Ticket ticket = new Ticket();
        ticket.setTicketId(id);
        Example example = Example.of(ticket);
        return (Ticket) ticketRepository.findOne(example).get();
    }

    public Ticket updateTicket(long id, Ticket ticket) {
        Ticket updatedTicket = findTicket(id);
        if (!ticket.getDescription().equals(updatedTicket.getDescription())) {
            updatedTicket.setDescription(ticket.getDescription());
            return ticketRepository.save(updatedTicket);
        } else
            return null;
    }

    public void deleteById(long id) {
        Ticket ticket = new Ticket();
        ticket.setTicketId(id);
        ticketRepository.delete(ticket);
    }

    @Autowired
    private CommentRepository commentRepository;

    // --------------------------------------------
    // CRUD OPERATIONS FOR CHILD RECORDS (COMMENTS)

    public Ticket createComment(long ticketId, Comment comment) {
        Ticket ticket = findTicket(ticketId);
        comment.setTicket(ticket);
        ticket.getComments().add(comment);

        return ticketRepository.save(ticket);
    }

    public List<Comment> findAllComments(long ticketId) {
        return findTicket(ticketId).getComments();
    }

    public Comment findComment(long id) {
        Comment comment = new Comment();
        comment.setCommentId(id);
        return commentRepository.findOne(Example.of(comment)).get();
    }

    public Comment updateComment(long commentId, Comment comment) {
        Comment savedComment = commentRepository.findOne(Example.of(comment)).get();
        savedComment.setText(comment.getText());
        commentRepository.save(savedComment);
        return savedComment;
    }

    public void deleteCommentById(long id) {
        Comment comment = new Comment();
        comment.setCommentId(id);
        commentRepository.delete(comment);
    }
}