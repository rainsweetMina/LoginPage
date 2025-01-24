package kroryi.loginpage.Dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MyDB {

    private static final String URL ="jdbc:mysql://localhost:3306/member?useSSL=false&useUnicode=true&characterEncoding=utf8&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASS = "1333";

    private static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(URL,USER,PASS);
    };

    private static List<Member> listMember;

    public MyDB() {
        listMember = new ArrayList<>();
    }


    public static int chkIdPw(String id, String pw) {
        String query = "SELECT * FROM member WHERE id =?";
        int result = 0;
        try(
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)
                ){
            pstmt.setString(1, id);
            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()) {
                    result += 1;
                    if(pw.equals(rs.getString("pw"))) {
                        result += 1;
                    };
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static void saveMember(Member member) {
        String sql = "INSERT INTO member (name, id, pw, email) VALUES (?, ?, ?, ?)";

        try(
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ){
            pstmt.setString(1,member.getName());
            pstmt.setString(2,member.getId());
            pstmt.setString(3,member.getPw());
            pstmt.setString(4,member.getEmail());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        listMember.add(member);
    }

    public static List<Member> getListMember() {
        String query = "select * from member";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)
        ) {
            listMember.clear();
            while (resultSet.next()) {
                listMember.add(new Member(
                        resultSet.getString("name"),
                        resultSet.getString("id"),
                        resultSet.getString("pw"),
                        resultSet.getString("email")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listMember;
    }


    public static List<Member> getListMemberPage(int pageIndex) {
        String query = "select * from member order by id desc limit ? offset ?";

        try(Connection connection = getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query);
            ) {
            pstmt.setInt(1, 10);
            pstmt.setInt(2,pageIndex * 10);

            listMember.clear();
            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                   listMember.add(new Member(
                           rs.getString("name"),
                           rs.getString("id"),
                           rs.getString("pw"),
                           rs.getString("email")
                   ));
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }


        return listMember;
    }

    public static void updateMember(Member member) {
        String query = "UPDATE member SET name = ?, id = ?, pw = ?, email = ? WHERE id = ?";

        try(
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(query);
                )
        {
            ps.setString(1,member.getName());
            ps.setString(2,member.getId());
            ps.setString(3,member.getPw());
            ps.setString(4,member.getEmail());
            ps.setString(5,member.getId());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }



//        for(Member m : listMember) {
//            if(m.getId().equals(member.getId())) {
//                m.setName(member.getName());
//                m.setId(member.getId());
//                m.setPw(member.getPw());
//                m.setEmail(member.getEmail());
//                return;
//            }
//        }
//        listMember.toString();
//        System.out.println("회원정보 수정 ID:" + member.getId() + "찾을 수 없음.");

    }

     public static void deleteMember(Member member) {
        String sql = "DELETE FROM member WHERE id = ?";
        try(
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ){
            ps.setString(1,member.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
     }

    public static ObservableList<Member> getObservableListMember() {
        List<Member> members = getListMember(); // 기존 메서드 호출
        return FXCollections.observableArrayList(members);
        // List<Member> ObservableList로 변환
    }

    public static ObservableList<Member> getObservableListMemberPage(int pageIndex) {
        List<Member> members = getListMemberPage(pageIndex); // 기존 메서드 호출
        return FXCollections.observableArrayList(members);
        // List<Member> ObservableList로 변환
    }

    public static int selectTatalCount() {
        String query = "SELECT COUNT(*) FROM member ORDER BY id";
        int count = 0;
        try(
                Connection conn = getConnection();
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                ){
            if(resultSet.next()) {
                count = resultSet.getInt(1);
            }
            System.out.println("총 게시물: " + count);


        }catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

}
