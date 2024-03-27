import React, { useState, useEffect } from 'react';
import { getMotionUrl } from '../../api/stopmotionApi';

const VideoComponent = () => {
    const [fileName, setFileName] = useState('');
    const [showModal, setShowModal] = useState(false);
    const [s3Url, setS3Url] = useState('');

    useEffect(() => {
        async function fetchS3Url() {
            try {
                const url = await getMotionUrl(); // API를 호출하여 S3 URL을 받아옴
                setS3Url(url);
            } catch (error) {
                console.error('Error fetching S3 URL:', error);
            }
        }

        fetchS3Url(); // 컴포넌트가 마운트될 때 S3 URL을 받아옴
    }, []);

    const handleDownload = () => {
        setShowModal(true);
    };

    const handleConfirmDownload = () => {
        if (fileName.trim() !== '') {
            downloadVideo();
            setShowModal(false);
        } else {
            alert('파일 이름을 입력하세요.');
        }
    };

    const downloadVideo = () => {
        try {
            const a = document.createElement('a');
            a.href = s3Url;
            a.download = fileName || 'video.mp4'; // 사용자가 입력한 파일명 또는 기본 파일명 설정
            document.body.appendChild(a);
            a.click();
            document.body.removeChild(a);
        } catch (error) {
            console.error('Error downloading video:', error);
        }
    };

    const handleChange = (event) => {
        setFileName(event.target.value);
    };

    return (
        <div>
            <h1>영상 다운로드</h1>
            <button onClick={handleDownload}>영상 다운로드</button>

            {showModal && (
                <div className="modal">
                    <div className="modal-content">
                        <h2>파일 이름 입력</h2>
                        <input
                            type="text"
                            placeholder="파일명을 입력하세요"
                            value={fileName}
                            onChange={handleChange}
                        />
                        <button onClick={handleConfirmDownload}>다운로드</button>
                        <button onClick={() => setShowModal(false)}>취소</button>
                    </div>
                </div>
            )}
        </div>
    );
};

export default VideoComponent;
