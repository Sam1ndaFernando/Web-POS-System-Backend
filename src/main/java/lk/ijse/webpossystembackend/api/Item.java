package lk.ijse.webpossystembackend.api;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "item", urlPatterns = "/item", loadOnStartup = 5)
public class Item extends HttpServlet {

    Logger logger = LoggerFactory.getLogger(Item.class);
    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);
    private Connection connection;
    Jsonb jsonb = JsonbBuilder.create();


    @Override
    public void init() throws ServletException {
        logger.info("Init the item servlet");

        try {

            InitialContext context = new InitialContext();
            DataSource pool = (DataSource) context.lookup("java:comp/env/jdbc/javaee_pos");
            this.connection = pool.getConnection();
            logger.info("Obtained new connection (" + connection + ") to item servlet");

        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<ItemDTO> items = itemBO.getAllItems(connection);

            if (!items.isEmpty()){
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");

                PrintWriter writer = resp.getWriter();
                String json = jsonb.toJson(items);
                writer.write(json);

                logger.info("Items data send");
                resp.setStatus(HttpServletResponse.SC_OK);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getContentType() != null && req.getContentType().toLowerCase().startsWith("application/json")){
            ItemDTO itemDTO = jsonb.fromJson(req.getReader(), ItemDTO.class);

            try {
                if (itemBO.createItem(itemDTO, connection)){
                    logger.info("Item is Saved");
                    resp.setStatus(HttpServletResponse.SC_OK);
                }else {
                    logger.info("Failed to save");
                    resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }else{
            logger.error("Did not contain json ContentType");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getContentType() != null && req.getContentType().toLowerCase().startsWith("application/json")){
            ItemDTO itemDTO = jsonb.fromJson(req.getReader(), ItemDTO.class);

            try {
                if (itemBO.updateItem(itemDTO, connection)){
                    logger.info("Item is Updated");
                    resp.setStatus(HttpServletResponse.SC_OK);
                }else{
                    logger.error("Failed to Update");
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }

        }else {
            logger.error("Did not contain json ContentType");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getContentType() != null && req.getContentType().toLowerCase().startsWith("application/json")){
            ItemDTO itemDTO = jsonb.fromJson(req.getReader(), ItemDTO.class);

            try {
                if (itemBO.deleteItem(itemDTO.getItemId(), connection)){
                    logger.info("Item is Deleted");
                    resp.setStatus(HttpServletResponse.SC_OK);
                }else{
                    logger.error("Failed to Delete");
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }

        }else {
            logger.error("Did not contain json ContentType");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
