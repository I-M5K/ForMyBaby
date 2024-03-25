const express = require('express');
const app = express();
const http = require('http').Server(app);
const io = require('socket.io')(http);
const multer = require('multer');
const fs = require('fs');

// 방 정보를 저장할 객체
const rooms = {};

// WebSocket 연결
io.on('connection', (socket) => {
  console.log('A client connected');

  // 이미지 데이터 수신
  socket.on('image', (data) => {
    console.log('Received image data');
    // 이미지 데이터를 클라이언트로 전송
    socket.emit('image', data);
  });

  // 클라이언트로부터 아이디 수신
  socket.on('babyId', (babyId) => {
    console.log(`Received babyId: ${babyId}`);
    
    // 해당 아이디와 같은 방이 없으면 방 생성
    if (!rooms[babyId]) {
      rooms[babyId] = [socket.id];
      console.log(`Created room for babyId: ${babyId}`);

      // uploads 폴더 밑에 babyId와 같은 이름의 폴더 생성
      const folderPath = `uploads/${babyId}`;
      fs.mkdir(folderPath, { recursive: true }, (err) => {
        if (err) {
          console.error(`Error creating folder for babyId ${babyId}:`, err);
        } else {
          console.log(`Created folder for babyId: ${babyId}`);
        }
      });
    } else {
      // 이미 해당 아이디와 같은 방이 있으면 해당 방에 소켓 추가
      rooms[babyId].push(socket.id);
      console.log(`Added socket to room for babyId: ${babyId}`);
    }
  });

  // 연결 종료 시 처리
  socket.on('disconnect', () => {
    console.log('A client disconnected');

    // 소켓이 속한 방 찾아서 소켓 제거
    for (const [roomId, sockets] of Object.entries(rooms)) {
      const index = sockets.indexOf(socket.id);
      if (index !== -1) {
        sockets.splice(index, 1);
        console.log(`Removed socket from room: ${roomId}`);
        // if (sockets.length === 0) {
        //   // 방에 소켓이 없으면 방 삭제
        //   delete rooms[roomId];
        //   console.log(`Deleted room: ${roomId}`);
        // }
        break;
      }
    }
  });
});

const storage = multer.diskStorage({
  destination: (req, file, cb) => {
    // 클라이언트로부터 전달된 babyId를 확인하여 해당 폴더에 저장
    const babyId = req.body.babyId;
    const destinationPath = `uploads/${babyId}`;
    // 폴더가 없으면 생성
    fs.mkdir(destinationPath, { recursive: true }, (err) => {
      if (err) {
        console.error(`Error creating folder for babyId ${babyId}:`, err);
      }
    });
    // 저장할 경로를 설정
    cb(null, destinationPath);
  },
  filename: (req, file, cb) => {
    cb(null, file.originalname);
  }
});


// 이미지를 받을 라우트 설정
const upload = multer({ storage: storage });
app.post('/data/:babyId', upload.single('image'), (req, res) => {
  try {
    const babyId = req.params.babyId;
    // console.log('Received image for babyId:', babyId);

    const line = req.body.line; // line 데이터 수신
    // console.log('Received line:', line);

    const timestamp = req.body.timestamp; // timestamp 데이터 수신
    // console.log('Received timestamp:', timestamp);


    console.log(timestamp, babyId, line); // Combine into one line

    // 이미지 파일을 읽어서 데이터를 클라이언트로 전송
    fs.readFile(req.file.path, (err, data) => {
      if (err) throw err;
      // 이미지와 함께 데이터를 클라이언트로 전송
      io.emit('image', { imageData: data, lineData: line, timestamp: timestamp });
    });
    res.status(200).send('Image received');
  } catch (error) {
    console.error(error);
    res.status(500).send('An error occurred while processing the image.');
  }
});

// 서버 실행
const PORT = process.env.PORT || 3001;
http.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);
});
