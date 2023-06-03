/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import domain.Coach;
import domain.Excercise;
import domain.Member;
import domain.TrainingSession;
import form.FrmViewAll;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class Communication {
    private static Communication instance;
    private Socket socket;
    private Sender sender;
    private Receiver receiver;

    private Communication() throws IOException {
        this.socket = new Socket("localhost", 9000);
        this.sender = new Sender(socket);
        this.receiver = new Receiver(socket);
    }
    
    public static Communication getInstance() throws Exception{
    if(instance==null) instance= new Communication();
    
    return instance;
    }

    public Coach login(String username, String password) throws Exception {
        Coach c = new Coach();
        c.setUsername(username);
        c.setPassword(password);
        Request request = new Request(Operation.LOGIN, c);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            return (Coach) response.getResult();
        }else{
            throw response.getException();
        }
    }
    
    public void logout(Coach user) throws Exception {
        Request request = new Request(Operation.LOG_OUT, user);
        sender.send(request);
    }
    
    public void connect() {
        try {
            this.socket = new Socket("localhost", 9000);
            this.sender = new Sender(socket);
            this.receiver = new Receiver(socket);
        } catch (IOException ex) {
            Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public List<Member> getMembers() {
        try {
            Request request = new Request(Operation.GET_ALL_MEMBERS, null);
            sender.send(request);
            Response response = (Response) receiver.receive();
            
           return (List<Member>) response.getResult();
        } catch (Exception ex) {
            Logger.getLogger(FrmViewAll.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public Member loadMember(Member m) throws Exception{
        Request request = new Request(Operation.LOAD_MEMBER, m);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException()!=null){
           throw response.getException();
        }else{
            return (Member) response.getResult();
        }
    }
    
    public List<Coach> getCoaches() throws Exception {
        
        Request request = new Request(Operation.GET_ALL_COACHES, null);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if(response.getException()!=null){
           throw response.getException();
        }else{
            return (List<Coach>) response.getResult();
        }
    }
    
    public Coach loadCoach(Coach c) throws Exception {
        Request request = new Request(Operation.LOAD_COACH, c);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException()!=null){
           throw response.getException();
        }else{
            return (Coach) response.getResult();
        }
    }
    
    public List<TrainingSession> getSessions() throws Exception {
        
        Request request = new Request(Operation.GET_ALL_TRAINING_SESSIONS, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
        
        if(response.getException()!=null){
           throw response.getException();
        }else{
            return (List<TrainingSession>) response.getResult();
        }
    }
    
    public TrainingSession loadSession(TrainingSession ts) throws Exception{
        Request request = new Request(Operation.LOAD_TRAINING_SESSION, ts);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException()!=null){
           throw response.getException();
        }else{
            return (TrainingSession) response.getResult();
        }
    }
    
    public void addCoach(Coach c) throws Exception{
        Request request = new Request(Operation.ADD_COACH, c);
            sender.send(request);
            Response response = (Response) receiver.receive();
            
            if(response.getException()!=null){
               throw response.getException();
            }
    }
    
    public void editCoach(Coach c) throws Exception{
        Request request = new Request(Operation.EDIT_COACH, c);
            sender.send(request);
            Response response = (Response) receiver.receive();
            
            if(response.getException()!=null){
               throw response.getException();
            }
    }
    
    public TrainingSession getTrainingSession(TrainingSession ts) throws Exception{
        Request request = new Request(Operation.GET_TRAINING_SESSION, ts);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException()!=null){
               throw response.getException();
        }
        else{
            return (TrainingSession) response.getResult();
        }
    }
    ///////
    public void addMember(Member m) throws Exception{
        Request request = new Request(Operation.ADD_MEMBER, m);
            sender.send(request);
            Response response = (Response) receiver.receive();
            
            if(response.getException()!=null){
               throw response.getException();
            }
    }
    
    public void editMember(Member m) throws Exception{
        Request request = new Request(Operation.EDIT_MEMBER, m);
            sender.send(request);
            Response response = (Response) receiver.receive();
            
            if(response.getException()!=null){
               throw response.getException();
            }
    }
    
    public void addTrainingSession(TrainingSession session) throws Exception{
        Request request = new Request(Operation.ADD_TRAINING_SESSION, session);
            sender.send(request);
            Response response = (Response) receiver.receive();
            
            if(response.getException()!=null){
               throw response.getException();
            }
    }
    
    public List<Excercise> getExcercises() throws Exception{
        Request request1 = new Request(Operation.GET_ALL_EXCERCISES,null);
            sender.send(request1);
            Response response = (Response) receiver.receive();
            if(response.getException()!=null){
               throw response.getException();
            }else{
                return (List<Excercise>)response.getResult();
            }
    }

    public List<Member> getMembers(String search) {
        try {
            Request request = new Request(Operation.SEARCH_MEMBERS, search);
            sender.send(request);
            Response response = (Response) receiver.receive();
            
           return (List<Member>) response.getResult();
        } catch (Exception ex) {
            Logger.getLogger(FrmViewAll.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Coach> getCoaches(String search) {
        try {
            Request request = new Request(Operation.SEARCH_COACHES, search);
            sender.send(request);
            Response response = (Response) receiver.receive();
            
//            if(response.getException()==null){
//                JOptionPane.showMessageDialog(this,"Members successfully loaded");
//            }else{
//               throw response.getException();
//            }
           return (List<Coach>) response.getResult();
        } catch (Exception ex) {
            Logger.getLogger(FrmViewAll.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public void editTrainingSession(TrainingSession ts) throws Exception{
        Request request = new Request(Operation.EDIT_TRAINING_SESSION, ts);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException()!=null){
            throw response.getException();
        }
    }

    public void deleteMember(Member member) throws Exception {
        Request request = new Request(Operation.DELETE_MEMBER, member);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException()!=null){
            throw response.getException();
        }
        
    }

    public void deleteTrainingSession(TrainingSession session) throws Exception {
        Request request = new Request(Operation.DELETE_TRAINING_SESSION,session);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException()!=null){
            throw response.getException();
        }
    }

    public List<TrainingSession> getSessions(String search) {
        try {
            Request request = new Request(Operation.SEARCH_TRAINING_SESSIONS, search);
            sender.send(request);
            Response response = (Response) receiver.receive();
            
           return (List<TrainingSession>) response.getResult();
        } catch (Exception ex) {
            Logger.getLogger(FrmViewAll.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    

    
    
}
