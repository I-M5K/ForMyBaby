export function getPostWord(str, firstVal, secondVal) {
    try {
        const laststr = str.charAt(str.length - 1);
        // 한글의 제일 처음과 끝의 범위밖일 경우는 오류
        if (laststr < 0xAC00 || laststr > 0xD7A3) {
            return str;
        }

        const lastCharIndex = (laststr - 0xAC00) % 28;

        // 종성인덱스가 0이상일 경우는 받침이 있는경우이며 그렇지 않은경우는 받침이 없는 경우
        if (lastCharIndex > 0) {
            // 받침이 있는경우
            // 조사가 '으로'인 경우 'ㄹ'받침으로 끝나는 경우는 '로' 나머지 경우는 '으로'
            if (firstVal === "으로" && lastCharIndex === 8) {
                str += secondVal;
            } else {
                str += firstVal;
            }
        } else {
            // 받침이 없는 경우
            str += secondVal;
        }
    } catch (error) {
        // 에러 처리
        //console.error(error);
    }

    return str;
}
