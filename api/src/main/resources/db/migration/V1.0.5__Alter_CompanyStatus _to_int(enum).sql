ALTER TABLE companies ALTER company_status SET DEFAULT NULL;
ALTER TABLE companies
ALTER  company_status TYPE INTEGER
USING
CASE
WHEN company_status IS NULL THEN NULL WHEN company_status THEN 1 ELSE 2
END;
