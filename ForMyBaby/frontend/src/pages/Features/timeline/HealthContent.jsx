import React, { useState, useEffect } from "react";
import { useUserStore } from "../../../stores/UserStore";

const HealthContent = () => {
  const context = [
    {
      id: 1,
      end_at: 35,
      health_title: "1차 건강검진",
      start_at: 14,
      category: "1차 검진",
    },
    {
      id: 2,
      end_at: 6,
      health_title: "2차 건강검진",
      start_at: 4,
      category: "2차 검진",
    },
    {
      id: 3,
      end_at: 12,
      health_title: "3차 건강검진",
      start_at: 9,
      category: "3차 검진",
    },
    {
      id: 4,
      end_at: 24,
      health_title: "4차 건강검진",
      start_at: 18,
      category: "4차 검진",
    },
    {
      id: 5,
      end_at: 29,
      health_title: "4차 구강검진",
      start_at: 18,
      category: "4차 검진",
    },
    {
      id: 6,
      end_at: 36,
      health_title: "5차 건강검진",
      start_at: 30,
      category: "5차 검진",
    },
    {
      id: 7,
      end_at: 41,
      health_title: "5차 구강검진",
      start_at: 30,
      category: "5차 검진",
    },
    {
      id: 8,
      end_at: 48,
      health_title: "6차 건강검진",
      start_at: 42,
      category: "6차 검진",
    },
    {
      id: 9,
      end_at: 53,
      health_title: "6차 구강검진",
      start_at: 42,
      category: "6차 검진",
    },
    {
      id: 10,
      end_at: 60,
      health_title: "7차 건강검진",
      start_at: 54,
      category: "7차 검진",
    },
    {
      id: 11,
      end_at: 65,
      health_title: "7차 구강검진",
      start_at: 54,
      category: "7차 검진",
    },
    {
      id: 12,
      end_at: 71,
      health_title: "8차 건강검진",
      start_at: 66,
      category: "8차 검진",
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
        {context.map((health) => {
          // birth가 start와 end 사이에 있는지 확인하는 부분
          let start = new Date();
          let end = new Date();
          const today = new Date();
          if (health.id === 1) {
            start = new Date(birth.getFullYear(), birth.getMonth(), birth.getDate() + health.start_at);
            end = new Date(birth.getFullYear(), birth.getMonth(), birth.getDate() + health.end_at);
          } else {
            start = new Date(birth.getFullYear(), birth.getMonth() + health.start_at, birth.getDate());
            end = new Date(birth.getFullYear(), birth.getMonth() + health.end_at, birth.getDate());
          }

          return (
            <>
              <tr>
                {[4, 6, 8, 10].find((e) => e === health.id) > -1 ? (
                  <td
                    rowSpan={4}
                    style={{
                      borderRight: "1px solid #CCCCCC",
                      borderBottom: "1px solid #CCCCCC",
                      width: "35%",
                      height: "200px",
                      fontWeight: "bold",
                      fontSize: 20,
                      backgroundColor: today >= start && today <= end ? "#DDF7F7" : "inherit",
                    }}
                  >
                    {health.category}
                  </td>
                ) : [5, 7, 9, 11].find((e) => e === health.id) > -1 ? (
                  <></>
                ) : (
                  <td
                    rowSpan={2}
                    style={{
                      borderRight: "1px solid #CCCCCC",
                      borderBottom: "1px solid #CCCCCC",
                      width: "35%",
                      height: "100px",
                      fontWeight: "bold",
                      fontSize: 20,
                      backgroundColor: today >= start && today <= end ? "#DDF7F7" : "inherit",
                    }}
                  >
                    {health.category}
                  </td>
                )}
                {health.id === 1 ? (
                  <td
                    style={{
                      borderLeft: "1px solid #CCCCCC",
                      textAlign: "left",
                      paddingLeft: 20,
                      color: "#CCCCCC",
                      fontWeight: "bold",
                      fontSize: 16,
                      backgroundColor: today >= start && today <= end ? "#DDF7F7" : "inherit",
                    }}
                  >
                    <>
                      {start.getFullYear()}-{String(start.getMonth() + 1).padStart(2, "0")}-{String(start.getDate()).padStart(2, "0")} ~{" "}
                      {end.getFullYear()}-{String(end.getMonth() + 1).padStart(2, "0")}-{String(end.getDate()).padStart(2, "0")}
                    </>
                  </td>
                ) : (
                  <td
                    style={{
                      borderLeft: "1px solid #CCCCCC",
                      textAlign: "left",
                      paddingLeft: 20,
                      color: "#CCCCCC",
                      fontWeight: "bold",
                      fontSize: 16,
                    }}
                  >
                    <>
                      {start.getFullYear()}-{String(start.getMonth() + 1).padStart(2, "0")}-{String(start.getDate()).padStart(2, "0")} ~{" "}
                      {end.getFullYear()}-{String(end.getMonth() + 1).padStart(2, "0")}-{String(end.getDate()).padStart(2, "0")}
                    </>
                  </td>
                )}
              </tr>
              <tr>
                <td
                  style={{
                    borderLeft: "1px solid #CCCCCC",
                    borderBottom: "1px solid #CCCCCC",
                    textAlign: "left",
                    paddingLeft: 20,
                    fontWeight: "bold",
                    fontSize: 18,
                    verticalAlign: "top",
                    backgroundColor: today >= start && today <= end ? "#DDF7F7" : "inherit",
                  }}
                >
                  {health.health_title}
                </td>
              </tr>
            </>
          );
        })}
      </table>
    </div>
  );
};

export default HealthContent;
