import React, { useState, useEffect } from "react";
import { useUserStore } from "../../../stores/UserStore";

const VaccineContent = () => {
  const vaccines = [
    {
      id: 1,
      start_at: 0,
      end_at: 1,
      category: "출생~1개월",
      comment: 0,
      content: [
        { target: "결핵", vaccine: "BCG" },
        { target: "B형간염", vaccine: "HepB 1차" },
      ],
    },
    {
      id: 2,
      start_at: 1,
      end_at: 2,
      category: "1개월",
      comment: 0,
      content: [{ target: "B형간염", vaccine: "HepB 2차" }],
    },
    {
      id: 3,
      start_at: 2,
      end_at: 3,
      category: "2개월",
      comment: 0,
      content: [
        { target: "폐렴구균 감염증", vaccine: "PCV 1차" },
        { target: "b형 헤모필루스인플루엔자", vaccine: "Hib 1차" },
        { target: "폴리오", vaccine: "IPV 1차" },
        { target: "로타바이러스 감염증", vaccine: "RV 1차" },
        { target: "로타바이러스 감염증", vaccine: "RV5 1차" },
        { target: "디프테리아/파상풍/백일해", vaccine: "DTaP 1차" },
      ],
    },
    {
      id: 4,
      start_at: 4,
      end_at: 5,
      category: "4개월",
      comment: 0,
      content: [
        { target: "로타바이러스 감염증", vaccine: "RV1 2차" },
        { target: "로타바이러스 감염증", vaccine: "RV5 2차" },
        { target: "디프테리아/파상풍/백일해", vaccine: "DTaP 2차" },
        { target: "폴리오", vaccine: "IPV 2차" },
        { target: "폐렴구균 감염증", vaccine: "PCV 2차" },
        { target: "b형 헤모필루스인플루엔자", vaccine: "Hib 2차" },
      ],
    },
    {
      id: 5,
      start_at: 6,
      end_at: 7,
      category: "6개월",
      comment: 0,
      content: [
        { target: "로타바이러스 감염증", vaccine: "RV5 3차" },
        { target: "폐렴구균 감염증", vaccine: "PCV 3차" },
        { target: "b형 헤모필루스인플루엔자", vaccine: "Hib 3차" },
        { target: "디프테리아/파상풍/백일해", vaccine: "DTaP 3차" },
        { target: "B형간염", vaccine: "HepB 3차" },
      ],
    },
    {
      id: 6,
      start_at: 6,
      end_at: 19,
      category: "6~18개월",
      comment: 0,
      content: [{ target: "폴리오", vaccine: "IPV 3차" }],
    },
    {
      id: 7,
      start_at: 6,
      end_at: 144,
      category: "6개월~만 12세",
      comment: 1,
      content: [{ target: "인플루엔자", vaccine: "IIV", comment: "접종 첫 해 최소 4주 간격으로 2회 접종, 이후 매년 1회 접종" }],
    },
    {
      id: 8,
      start_at: 12,
      end_at: 16,
      category: "12~15개월",
      comment: 0,
      content: [
        { target: "수두", vaccine: "VAR 1차" },
        { target: "b형 헤모필루스인플루엔자", vaccine: "Hib 4차" },
        { target: "홍역/유행성이하선염/풍진", vaccine: "MMR 1차" },
        { target: "폐렴구균 감염증", vaccine: "PCV 4차" },
      ],
    },
    {
      id: 9,
      start_at: 12,
      end_at: 24,
      category: "12~23개월",
      comment: 2,
      content: [
        { target: "A형간염", vaccine: "HepA 1~2차", comment: "2차는 1차 접종으로부터 6개월 이상 경과한 후 접종" },
        { target: "일본뇌염", vaccine: "사백신 1~2차, 생백신 1차", comment: "사백신 1차 접종 1개월 후 2차 접종" },
      ],
    },

    {
      id: 10,
      start_at: 15,
      end_at: 19,
      category: "15~18개월",
      comment: 0,
      content: [{ target: "디프테리아/파상풍/백일해", vaccine: "DTaP 4차" }],
    },
    {
      id: 11,
      start_at: 24,
      end_at: 36,
      category: "24~35개월",
      comment: 1,
      content: [{ target: "일본뇌염", vaccine: "사백신 3차(추가), 생백신 2차", comment: "생백신 2차는 1차 접종 12개월 후 접종" }],
    },
    {
      id: 12,
      start_at: 48,
      end_at: 84,
      category: "만 4~6세",
      comment: 0,
      content: [
        { target: "디프테리아/파상풍/백일해", vaccine: "DTaP 5차" },
        { target: "폴리오", vaccine: "IPV 4차" },
        { target: "홍역/유행성이하선염/풍진", vaccine: "MMR 2차" },
      ],
    },
    {
      id: 13,
      start_at: 72,
      end_at: 84,
      category: "만 6세",
      comment: 0,
      content: [{ target: "일본뇌염", vaccine: "사백신 4차(추가)" }],
    },
    {
      id: 14,
      start_at: 132,
      end_at: 156,
      category: "만 11~12세",
      comment: 1,
      content: [
        { target: "사람유두종 바이러스 감염증", vaccine: "HPV 1~2차", comment: ": 11~12세 여아에서 6~12개월 간격으로 2회 접종" },
        { target: "디프테리아/파상풍/백일해", vaccine: "Tdap/Td 6차" },
      ],
    },
    {
      id: 14,
      start_at: 144,
      end_at: 156,
      category: "만 12세",
      comment: 0,
      content: [{ target: "일본뇌염", vaccine: "사백신 5차(추가)" }],
    },
  ];

  const { babyList, babySelected } = useUserStore();
  const baby = babyList.find((baby) => baby.babyId === babySelected);
  const [birth, setBirth] = useState(new Date(baby.birthDate));

  useEffect(() => {
    if (baby) {
      setBirth(new Date(baby.birthDate));
    }
  }, [baby]);

  return (
    <div>
      <table style={{ borderCollapse: "collapse", width: "100%", marginBottom: 90 }}>
        {vaccines.map((vaccine) => {
          const today = new Date();
          const birth = new Date(); // birth 변수는 어디서 오는 것인지 확인 필요

          // birth가 start와 end 사이에 있는지 확인하는 부분
          let start = new Date(birth.getFullYear(), birth.getMonth() + vaccine.start_at, birth.getDate());
          let end = new Date(birth.getFullYear(), birth.getMonth() + vaccine.end_at, birth.getDate());

          return (
            <React.Fragment key={vaccine.id}>
              <tr>
                {/* 연령 */}
                <td
                  rowSpan={vaccine.comment + vaccine.content.length + 1}
                  style={{
                    borderRight: "1px solid #CCCCCC",
                    borderBottom: "1px solid #CCCCCC",
                    width: "35%",
                    fontWeight: "bold",
                    fontSize: 20,
                    backgroundColor: today >= start && today <= end ? "#DDF7F7" : "inherit",
                  }}
                >
                  {vaccine.category}
                </td>
                {/* 정보 */}
                <td
                  style={{
                    borderLeft: "1px solid #CCCCCC",
                    textAlign: "left",
                    height: 50,
                    paddingLeft: 20,
                    color: "#CCCCCC",
                    fontWeight: "bold",
                    fontSize: 16,
                    backgroundColor: today >= start && today <= end ? "#DDF7F7" : "inherit",
                  }}
                >
                  {start.getFullYear()}-{String(start.getMonth() + 1).padStart(2, "0")}-{String(start.getDate()).padStart(2, "0")} ~{" "}
                  {end.getFullYear()}-{String(end.getMonth() + 1).padStart(2, "0")}-{String(end.getDate()).padStart(2, "0")}
                </td>
              </tr>
              {vaccine.content.map((v, index) => (
                <React.Fragment key={index}>
                  <tr>
                    <td
                      style={{
                        borderLeft: "1px solid #CCCCCC",
                        borderBottom: v.comment ? "" : "1px solid #CCCCCC",
                        textAlign: "left",
                        height: 50,
                        paddingLeft: 20,
                        paddingBottom: 10,
                        fontWeight: "bold",
                        fontSize: 18,
                        verticalAlign: "bottom",
                        backgroundColor: today >= start && today <= end ? "#DDF7F7" : "inherit",
                      }}
                    >
                      {v.target + "(" + v.vaccine + ")"}
                    </td>
                  </tr>
                  {v.comment ? (
                    <tr>
                      <td
                        style={{
                          borderLeft: "1px solid #CCCCCC",
                          borderBottom: "1px solid #CCCCCC",
                          textAlign: "left",
                          height: 50,
                          paddingLeft: 20,
                          fontSize: 15,
                          verticalAlign: "top",
                          color: "#CCCCCC",
                          backgroundColor: today >= start && today <= end ? "#DDF7F7" : "inherit",
                        }}
                      >
                        {v.comment}
                      </td>
                    </tr>
                  ) : (
                    <></>
                  )}
                </React.Fragment>
              ))}
            </React.Fragment>
          );
        })}
      </table>
    </div>
  );
};

export default VaccineContent;
