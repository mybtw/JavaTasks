package ru.astaf.roommanagementservice;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.astaf.roommanagementservice.dto.RoomRequest;
import ru.astaf.roommanagementservice.dto.RoomResponse;
import ru.astaf.roommanagementservice.models.Room;
import ru.astaf.roommanagementservice.repositories.RoomRepository;
import ru.astaf.roommanagementservice.sevices.RoomService;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.astaf.roommanagementservice.util.UtilFuncs.asJsonString;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class RoomManagementServiceApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoomService roomService;

    @Autowired
    private RoomRepository roomRepository;

    @BeforeAll
    static void setup() {
        mongoDBContainer.start();
        System.setProperty("spring.data.mongodb.uri", mongoDBContainer.getReplicaSetUrl());
    }

    @AfterAll
    static void tearDown() {
        mongoDBContainer.stop();
    }

    private Room room;

    @BeforeEach
    void setUp() {
        room = new Room("1", "Conference Room A", "101 First St", 10, 50, true, false);
    }

    @Container
    public static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");

    @Test
    void testGetAllRooms() throws Exception {
        List<RoomResponse> rooms = List.of(
                new RoomResponse(room.getId(), room.getName(), room.getFloor(), room.getAddress(), room.getCapacity(), room.isHasMonitorOrTV(), room.isHasVideoConferencing()),
                new RoomResponse("2", "Conference Room B", 9, "102 Second St", 45, true, true)
        );
        when(roomService.getAllRooms()).thenReturn(rooms);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/rooms"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(room.getId()));


        verify(roomService).getAllRooms();
    }


    @Test
    void testCreateRoom() throws Exception {
        RoomRequest roomRequest = new RoomRequest(room.getName(), room.getAddress(), room.getCapacity(), 5, room.isHasMonitorOrTV(), room.isHasVideoConferencing());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/rooms")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(roomRequest)))
                .andExpect(status().isCreated());

        verify(roomService).createRoom(any(RoomRequest.class));
    }



    @Test
    void testDeleteRoom() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/rooms/{roomId}", room.getId()))
                .andExpect(status().isNoContent());
        verify(roomService).deleteRoom(room.getId());
    }


    @Test
    void testUpdateRoom() throws Exception {
        RoomRequest updateRequest = new RoomRequest("Updated Room Name", room.getAddress(), 100, 8, room.isHasMonitorOrTV(), room.isHasVideoConferencing());
        when(roomService.getRoomById(room.getId())).thenReturn(new RoomResponse(room.getId(), room.getName(), room.getFloor(), room.getAddress(), room.getCapacity(), room.isHasMonitorOrTV(), room.isHasVideoConferencing()));

        mockMvc.perform(MockMvcRequestBuilders.put("/api/rooms/{roomId}", room.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updateRequest)))
                .andExpect(status().isOk());

        verify(roomService).updateRoom(eq(room.getId()), any(RoomRequest.class));
    }

}
