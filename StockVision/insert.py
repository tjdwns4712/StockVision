import pandas as pd
from sqlalchemy import create_engine

# 엑셀 파일을 읽어 데이터프레임으로 저장, 첫 줄부터 바로 데이터로 인식.
df = pd.read_excel("/Users/Zen1/leeseongjun/KOSDAQ.xlsx", header=None)

# 기업 이름과 종목 코드를 가져와 딕셔너리로 타입으로 저장.(기업이름:종목코드)순으로 저장.
company_tickers = dict(zip(df[1], df[0]))

# 딕셔너리의 값(종목 코드)에 ".KQ"를 추가하여 yfinance에 사용할 수 있는 타입의 새로운 딕셔너리 생성
yfinance_tickers = {company: ticker + ".KQ" for company, ticker in company_tickers.items()}

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

# 딕셔너리를 데이터프레임으로 변환
df_to_insert = pd.DataFrame(list(yfinance_tickers.items()), columns=["stock_name", "stock_code"])

# 데이터프레임을 MySQL 테이블에 삽입
df_to_insert.to_sql(table_name, engine, if_exists='append', index=False)

print("데이터가 MySQL 데이터베이스에 성공적으로 삽입되었습니다.")
