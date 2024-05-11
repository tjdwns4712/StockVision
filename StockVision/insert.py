import pandas as pd
from sqlalchemy import create_engine

# 엑셀 파일 읽기
excel_file_path = "/Users/Zen1/leeseongjun/KOSPI_yfinance_ticker.xlsx"  # 엑셀 파일 경로
df = pd.read_excel(excel_file_path, header=None)  # 첫 행을 열 이름으로 사용

# 열 이름을 기존 테이블에 맞게 변경
df.columns = ["stock_name", "stock_code"]  # 기존 테이블 구조에 맞춤

# MySQL 연결 정보
mysql_user = "root"
mysql_password = "12345678"
mysql_host = "localhost"  # 또는 다른 호스트 주소
mysql_database = "StockVision"

# MySQL 엔진 생성
connection_string = f"mysql+pymysql://{mysql_user}:{mysql_password}@{mysql_host}/{mysql_database}"
engine = create_engine(connection_string)

# 테이블 이름
table_name = "stock"

# DataFrame을 MySQL 테이블에 삽입 (기존 테이블에 데이터 추가)
df.to_sql(table_name, engine, if_exists='append', index=False)  # 인덱스는 데이터베이스에 추가하지 않음

print("데이터가 MySQL 데이터베이스에 성공적으로 삽입되었습니다.")
