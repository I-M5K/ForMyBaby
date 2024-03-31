INSERT INTO health (health_title, start_at, end_at)
SELECT '1차 건강검진', 14, 35
FROM DUAL
WHERE NOT EXISTS (SELECT * FROM health WHERE health_title = '1차 건강검진');

-- 2차 건강검진
INSERT INTO health (health_title, start_at, end_at)
SELECT '2차 건강검진', 4, 6
FROM DUAL
WHERE NOT EXISTS (SELECT * FROM health WHERE health_title = '2차 건강검진');

-- 3차 건강검진
INSERT INTO health (health_title, start_at, end_at)
SELECT '3차 건강검진', 9, 12
FROM DUAL
WHERE NOT EXISTS (SELECT * FROM health WHERE health_title = '3차 건강검진');

-- 4차 건강검진
INSERT INTO health (health_title, start_at, end_at)
SELECT '4차 건강검진', 18, 24
FROM DUAL
WHERE NOT EXISTS (SELECT * FROM health WHERE health_title = '4차 건강검진');

-- 4차 구강검진
INSERT INTO health (health_title, start_at, end_at)
SELECT '4차 구강검진', 18, 29
FROM DUAL
WHERE NOT EXISTS (SELECT * FROM health WHERE health_title = '4차 구강검진');

-- 5차 건강검진
INSERT INTO health (health_title, start_at, end_at)
SELECT '5차 건강검진', 30, 36
FROM DUAL
WHERE NOT EXISTS (SELECT * FROM health WHERE health_title = '5차 건강검진');

-- 5차 구강검진
INSERT INTO health (health_title, start_at, end_at)
SELECT '5차 구강검진', 30, 41
FROM DUAL
WHERE NOT EXISTS (SELECT * FROM health WHERE health_title = '5차 구강검진');

-- 6차 건강검진
INSERT INTO health (health_title, start_at, end_at)
SELECT '6차 건강검진', 42, 48
FROM DUAL
WHERE NOT EXISTS (SELECT * FROM health WHERE health_title = '6차 건강검진');

-- 6차 구강검진
INSERT INTO health (health_title, start_at, end_at)
SELECT '6차 구강검진', 42, 53
FROM DUAL
WHERE NOT EXISTS (SELECT * FROM health WHERE health_title = '6차 구강검진');

-- 7차 건강검진
INSERT INTO health (health_title, start_at, end_at)
SELECT '7차 건강검진', 54, 60
FROM DUAL
WHERE NOT EXISTS (SELECT * FROM health WHERE health_title = '7차 건강검진');

-- 7차 구강검진
INSERT INTO health (health_title, start_at, end_at)
SELECT '7차 구강검진', 54, 65
FROM DUAL
WHERE NOT EXISTS (SELECT * FROM health WHERE health_title = '7차 구강검진');

-- 8차 건강검진
INSERT INTO health (health_title, start_at, end_at)
SELECT '8차 건강검진', 66, 71
FROM DUAL
WHERE NOT EXISTS (SELECT * FROM health WHERE health_title = '8차 건강검진');


INSERT INTO vaccine (target, type, start_at, end_at)
SELECT 'B형간염', 'HepB 1차', 0, 0
FROM dual
WHERE NOT EXISTS (SELECT * FROM vaccine WHERE target = 'B형간염' AND type = 'HepB 1차');

INSERT INTO vaccine (target, type, start_at, end_at)
SELECT 'B형간염', 'HepB 2차', 1, 2
FROM dual
WHERE NOT EXISTS (SELECT * FROM vaccine WHERE target = 'B형간염' AND type = 'HepB 2차');

INSERT INTO vaccine (target, type, start_at, end_at)
SELECT 'B형간염', 'HepB 3차', 6, 7
FROM dual
WHERE NOT EXISTS (SELECT * FROM vaccine WHERE target = 'B형간염' AND type = 'HepB 3차');

INSERT INTO vaccine (target, type, start_at, end_at)
SELECT '결핵', 'BCG', 0, 1
FROM dual
WHERE NOT EXISTS (SELECT * FROM vaccine WHERE target = '결핵' AND type = 'BCG');

INSERT INTO vaccine (target, type, start_at, end_at)
SELECT '디프테리아/파상풍/백일해', 'DTaP 1차', 2, 3
FROM dual
WHERE NOT EXISTS (SELECT * FROM vaccine WHERE target = '디프테리아/파상풍/백일해' AND type = 'DTaP 1차');

INSERT INTO vaccine (target, type, start_at, end_at)
SELECT '디프테리아/파상풍/백일해', 'DTaP 2차', 4, 5
FROM dual
WHERE NOT EXISTS (SELECT * FROM vaccine WHERE target = '디프테리아/파상풍/백일해' AND type = 'DTaP 2차');

INSERT INTO vaccine (target, type, start_at, end_at)
SELECT '디프테리아/파상풍/백일해', 'DTaP 3차', 6, 7
FROM dual
WHERE NOT EXISTS (SELECT * FROM vaccine WHERE target = '디프테리아/파상풍/백일해' AND type = 'DTaP 3차');

INSERT INTO vaccine (target, type, start_at, end_at)
SELECT '디프테리아/파상풍/백일해', 'DTaP 4차', 15, 19
FROM dual
WHERE NOT EXISTS (SELECT * FROM vaccine WHERE target = '디프테리아/파상풍/백일해' AND type = 'DTaP 4차');

INSERT INTO vaccine (target, type, start_at, end_at)
SELECT '디프테리아/파상풍/백일해', 'DTaP 5차', 48, 84
FROM dual
WHERE NOT EXISTS (SELECT * FROM vaccine WHERE target = '디프테리아/파상풍/백일해' AND type = 'DTaP 5차');

INSERT INTO vaccine (target, type, start_at, end_at)
SELECT '디프테리아/파상풍/백일해', 'Tdap/Td 6차', 132, 156
FROM dual
WHERE NOT EXISTS (SELECT * FROM vaccine WHERE target = '디프테리아/파상풍/백일해' AND type = 'Tdap/Td 6차');

INSERT INTO vaccine (target, type, start_at, end_at)
SELECT '폴리오', 'IPV 1차', 2, 3
FROM dual
WHERE NOT EXISTS (SELECT * FROM vaccine WHERE target = '폴리오' AND type = 'IPV 1차');

INSERT INTO vaccine (target, type, start_at, end_at)
SELECT '폴리오', 'IPV 2차', 4, 5
FROM dual
WHERE NOT EXISTS (SELECT * FROM vaccine WHERE target = '폴리오' AND type = 'IPV 2차');

INSERT INTO vaccine (target, type, start_at, end_at)
SELECT '폴리오', 'IPV 3차', 6, 19
FROM dual
WHERE NOT EXISTS (SELECT * FROM vaccine WHERE target = '폴리오' AND type = 'IPV 3차');

INSERT INTO vaccine (target, type, start_at, end_at)
SELECT '폴리오', 'IPV 4차', 48, 84
FROM dual
WHERE NOT EXISTS (SELECT * FROM vaccine WHERE target = '폴리오' AND type = 'IPV 4차');

INSERT INTO vaccine (target, type, start_at, end_at)
SELECT 'b형 헤모필루스인플루엔자', 'Hib 1차', 2, 3
FROM dual
WHERE NOT EXISTS (SELECT * FROM vaccine WHERE target = 'b형 헤모필루스인플루엔자' AND type = 'Hib 1차');

INSERT INTO vaccine (target, type, start_at, end_at)
SELECT 'b형 헤모필루스인플루엔자', 'Hib 2차', 4, 5
FROM dual
WHERE NOT EXISTS (SELECT * FROM vaccine WHERE target = 'b형 헤모필루스인플루엔자' AND type = 'Hib 2차');

INSERT INTO vaccine (target, type, start_at, end_at)
SELECT 'b형 헤모필루스인플루엔자', 'Hib 3차', 6, 7
FROM dual
WHERE NOT EXISTS (SELECT * FROM vaccine WHERE target = 'b형 헤모필루스인플루엔자' AND type = 'Hib 3차');

INSERT INTO vaccine (target, type, start_at, end_at)
SELECT 'b형 헤모필루스인플루엔자', 'Hib 4차', 12, 16
FROM dual
WHERE NOT EXISTS (SELECT * FROM vaccine WHERE target = 'b형 헤모필루스인플루엔자' AND type = 'Hib 4차');

-- 폐렴구균 감염증
INSERT INTO vaccine (target, type, start_at, end_at)
SELECT '폐렴구균 감염증', 'PCV 1차', 2, 3 FROM dual
WHERE NOT EXISTS (SELECT * FROM vaccine WHERE target = '폐렴구균 감염증' AND type = 'PCV 1차');

INSERT INTO vaccine (target, type, start_at, end_at)
SELECT '폐렴구균 감염증', 'PCV 2차', 4, 5 FROM dual
WHERE NOT EXISTS (SELECT * FROM vaccine WHERE target = '폐렴구균 감염증' AND type = 'PCV 2차');

INSERT INTO vaccine (target, type, start_at, end_at)
SELECT '폐렴구균 감염증', 'PCV 3차', 6, 7 FROM dual
WHERE NOT EXISTS (SELECT * FROM vaccine WHERE target = '폐렴구균 감염증' AND type = 'PCV 3차');

INSERT INTO vaccine (target, type, start_at, end_at)
SELECT '폐렴구균 감염증', 'PCV 4차', 12, 16 FROM dual
WHERE NOT EXISTS (SELECT * FROM vaccine WHERE target = '폐렴구균 감염증' AND type = 'PCV 4차');

-- 로타바이러스 감염증
INSERT INTO vaccine (target, type, start_at, end_at)
SELECT '로타바이러스 감염증', 'RV 1차', 2, 3 FROM dual
WHERE NOT EXISTS (SELECT * FROM vaccine WHERE target = '로타바이러스 감염증' AND type = 'RV 1차');

INSERT INTO vaccine (target, type, start_at, end_at)
SELECT '로타바이러스 감염증', 'RV1 2차', 4, 5 FROM dual
WHERE NOT EXISTS (SELECT * FROM vaccine WHERE target = '로타바이러스 감염증' AND type = 'RV1 2차');

INSERT INTO vaccine (target, type, start_at, end_at)
SELECT '로타바이러스 감염증', 'RV5 1차', 2, 3 FROM dual
WHERE NOT EXISTS (SELECT * FROM vaccine WHERE target = '로타바이러스 감염증' AND type = 'RV5 1차');

INSERT INTO vaccine (target, type, start_at, end_at)
SELECT '로타바이러스 감염증', 'RV5 2차', 4, 5 FROM dual
WHERE NOT EXISTS (SELECT * FROM vaccine WHERE target = '로타바이러스 감염증' AND type = 'RV5 2차');

INSERT INTO vaccine (target, type, start_at, end_at)
SELECT '로타바이러스 감염증', 'RV5 3차', 6, 7 FROM dual
WHERE NOT EXISTS (SELECT * FROM vaccine WHERE target = '로타바이러스 감염증' AND type = 'RV5 3차');

-- 홍역/유행성이하선염/풍진
INSERT INTO vaccine (target, type, start_at, end_at)
SELECT '홍역/유행성이하선염/풍진', 'MMR 1차', 12, 16 FROM dual
WHERE NOT EXISTS (SELECT * FROM vaccine WHERE target = '홍역/유행성이하선염/풍진' AND type = 'MMR 1차');

INSERT INTO vaccine (target, type, start_at, end_at)
SELECT '홍역/유행성이하선염/풍진', 'MMR 2차', 48, 73 FROM dual
WHERE NOT EXISTS (SELECT * FROM vaccine WHERE target = '홍역/유행성이하선염/풍진' AND type = 'MMR 2차');

-- 수두
INSERT INTO vaccine (target, type, start_at, end_at)
SELECT '수두', 'VAR 1차', 12, 16 FROM dual
WHERE NOT EXISTS (SELECT * FROM vaccine WHERE target = '수두' AND type = 'VAR 1차');

-- A형간염
INSERT INTO vaccine (target, type, start_at, end_at)
SELECT 'A형간염', 'HepA 1차', 12, 24 FROM dual
WHERE NOT EXISTS (SELECT * FROM vaccine WHERE target = 'A형간염' AND type = 'HepA 1차');

INSERT INTO vaccine (target, type, start_at, end_at)
SELECT 'A형간염', 'HepA 2차', 24, 36 FROM dual
WHERE NOT EXISTS (SELECT * FROM vaccine WHERE target = 'A형간염' AND type = 'HepA 2차');

-- 일본뇌염
INSERT INTO vaccine (target, type, start_at, end_at)
SELECT '일본뇌염', 'IJEV(불활성화 백신) 1차', 12, 19 FROM dual
WHERE NOT EXISTS (SELECT * FROM vaccine WHERE target = '일본뇌염' AND type = 'IJEV(불활성화 백신) 1차');

INSERT INTO vaccine (target, type, start_at, end_at)
SELECT '일본뇌염', 'IJEV(불활성화 백신) 2차', 19, 24 FROM dual
WHERE NOT EXISTS (SELECT * FROM vaccine WHERE target = '일본뇌염' AND type = 'IJEV(불활성화 백신) 2차');

INSERT INTO vaccine (target, type, start_at, end_at)
SELECT '일본뇌염', 'IJEV(불활성화 백신) 3차', 24, 36 FROM dual
WHERE NOT EXISTS (SELECT * FROM vaccine WHERE target = '일본뇌염' AND type = 'IJEV(불활성화 백신) 3차');

INSERT INTO vaccine (target, type, start_at, end_at)
SELECT '일본뇌염', 'IJEV(불활성화 백신) 4차', 72, 84 FROM dual
WHERE NOT EXISTS (SELECT * FROM vaccine WHERE target = '일본뇌염' AND type = 'IJEV(불활성화 백신) 4차');

INSERT INTO vaccine (target, type, start_at, end_at)
SELECT '일본뇌염', 'IJEV(불활성화 백신) 5차', 144, 156 FROM dual
WHERE NOT EXISTS (SELECT * FROM vaccine WHERE target = '일본뇌염' AND type = 'IJEV(불활성화 백신) 5차');

INSERT INTO vaccine (target, type, start_at, end_at)
SELECT '일본뇌염', 'LJEV(약독화 생백신) 1차', 12, 24 FROM dual
WHERE NOT EXISTS (SELECT * FROM vaccine WHERE target = '일본뇌염' AND type = 'LJEV(약독화 생백신) 1차');

INSERT INTO vaccine (target, type, start_at, end_at)
SELECT '일본뇌염', 'LJEV(약독화 생백신) 2차', 24, 36 FROM dual
WHERE NOT EXISTS (SELECT * FROM vaccine WHERE target = '일본뇌염' AND type = 'LJEV(약독화 생백신) 2차');

-- 사람유두종 바이러스 감염증
INSERT INTO vaccine (target, type, start_at, end_at)
SELECT '사람유두종 바이러스 감염증', 'HPV 1차', 132, 144 FROM dual
WHERE NOT EXISTS (SELECT * FROM vaccine WHERE target = '사람유두종 바이러스 감염증' AND type = 'HPV 1차');

INSERT INTO vaccine (target, type, start_at, end_at)
SELECT '사람유두종 바이러스 감염증', 'HPV 2차', 144, 156 FROM dual
WHERE NOT EXISTS (SELECT * FROM vaccine WHERE target = '사람유두종 바이러스 감염증' AND type = 'HPV 2차');
