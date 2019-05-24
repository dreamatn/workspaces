package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFCTRM {
    DBParm BPTFCTR_RD;
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BP058";
    String WS_ERR_MSG = " ";
    short WS_CNT = 0;
    short WS_FLD_NO = 0;
    String WS_CTRT_NO = " ";
    String WS_CTRT_DESC = " ";
    String WS_CI_NO = " ";
    String WS_CTRT_TYPE = " ";
    String WS_FEE_TYPE = " ";
    int WS_BOOK_CENTRE = 0;
    String WS_PRD_TYPE = " ";
    int WS_START_DATE = 0;
    int WS_MATURITY_DATE = 0;
    char WS_HOLI_OVER = ' ';
    String WS_CAL_CODE1 = " ";
    char WS_HOLI_METHOD = ' ';
    String WS_CAL_CODE2 = " ";
    char WS_PAY_IND = ' ';
    char WS_CASHFLOW_IND = ' ';
    String WS_BANK_PORTF = " ";
    char WS_PAYMENT_METHOD = ' ';
    char WS_ACCRUAL_TYPE = ' ';
    char WS_PRICE_METHOD = ' ';
    String WS_TXN_CCY = " ";
    double WS_PRIN_AMT = 0;
    short WS_AMT_TYPE = 0;
    char WS_AGGR_TYPE = ' ';
    String WS_REF_CCY = " ";
    char WS_REF_METHOD = ' ';
    String WS_CHARGE_CCY = " ";
    double WS_CHARGE_AMT = 0;
    char WS_CHARGE_METHOD = ' ';
    int WS_CR_TO_BR = 0;
    String WS_CHARGE_AC = " ";
    String WS_CHQ_NO = " ";
    String WS_NOSTRO_AC = " ";
    String WS_CARD_PSBK_NO = " ";
    int WS_SALE_DATE = 0;
    char WS_CCY_TYPE = ' ';
    int WS_GL_MASTER1 = 0;
    int WS_GL_MASTER2 = 0;
    int WS_GL_MASTER3 = 0;
    int WS_GL_MASTER4 = 0;
    String WS_OIC_NO1 = " ";
    int WS_OIC_CENTRE1 = 0;
    double WS_OIC_PCT1 = 0;
    String WS_OIC_NO2 = " ";
    int WS_OIC_CENTRE2 = 0;
    double WS_OIC_PCT2 = 0;
    String WS_OIC_NO3 = " ";
    int WS_OIC_CENTRE3 = 0;
    double WS_OIC_PCT3 = 0;
    String WS_REMARK = " ";
    double WS_FEE_BAS_AMT = 0;
    String WS_CHG_CCY_REAL = " ";
    double WS_CHG_AMT_REAL = 0;
    String WS_TICKET = " ";
    double WS_RATE = 0;
    int WS_EXG_DATE = 0;
    int WS_EXG_TIME = 0;
    double WS_FCHG_MIN_AMT = 0;
    long WS_JNL_NO = 0;
    int WS_REAL_AC_DATE = 0;
    String WS_VOUCHER_NO = " ";
    int WS_CREATE_DATE = 0;
    String WS_CREATE_TELL = " ";
    int WS_UPDTBL_DATE = 0;
    String WS_LAST_TELL = " ";
    String WS_SUP_TEL1 = " ";
    String WS_SUP_TEL2 = " ";
    char WS_TBL_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCRFCTR BPCRFCTR = new BPCRFCTR();
    BPRFCTR BPRFCTR = new BPRFCTR();
    BPCSFECT BPCSFECT = new BPCSFECT();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCHFCTR BPCOFCTR = new BPCHFCTR();
    BPCHFCTR BPCNFCTR = new BPCHFCTR();
    String WS_REL_CTRT_NO = " ";
    char WS_FEE_STATUS = ' ';
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCCTRST BPCCTRST;
    public void MP(SCCGWA SCCGWA, BPCCTRST BPCCTRST) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCCTRST = BPCCTRST;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFCTRM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRFCTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_CHECK_ZERO_STATUS();
        if (pgmRtn) return;
        B030_UPDATE_MATURITY_DATE();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCCTRST.LN_CTRT_NO.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CTRT_NO_EMPTY;
            if (BPCCTRST.LN_CTRT_NO.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(BPCCTRST.LN_CTRT_NO);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_CHECK_ZERO_STATUS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCCTRST.LN_CTRT_NO);
        WS_REL_CTRT_NO = BPCCTRST.LN_CTRT_NO;
        WS_FEE_STATUS = '0';
        T000_READ_BPTFCTR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRFCTR.FEE_STATUS);
        CEP.TRC(SCCGWA, WS_FEE_STATUS);
        CEP.TRC(SCCGWA, BPRFCTR.KEY.CTRT_NO);
        CEP.TRC(SCCGWA, WS_TBL_FLAG);
        if (WS_TBL_FLAG == 'F') {
            CEP.TRC(SCCGWA, "123");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOTEFF_CONTRACT_EXIT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_UPDATE_MATURITY_DATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFCTR);
        WS_REL_CTRT_NO = BPCCTRST.LN_CTRT_NO;
        WS_FEE_STATUS = '1';
        T000_READ_BPTFCTR();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'F') {
            if (SCCGWA.COMM_AREA.AC_DATE < BPRFCTR.MATURITY_DATE) {
                T000_READ_BPTFCTR_UPD();
                if (pgmRtn) return;
                BPRFCTR.MATURITY_DATE = SCCGWA.COMM_AREA.AC_DATE;
                T000_REWRITE_BPTFCTR();
                if (pgmRtn) return;
                B031_TRANS_NHIS_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void T000_READ_BPTFCTR() throws IOException,SQLException,Exception {
        BPTFCTR_RD = new DBParm();
        BPTFCTR_RD.TableName = "BPTFCTR";
        BPTFCTR_RD.where = "REL_CTRT_NO = :WS_REL_CTRT_NO "
            + "AND FEE_STATUS = :WS_FEE_STATUS";
        BPTFCTR_RD.fst = true;
        IBS.READ(SCCGWA, BPRFCTR, this, BPTFCTR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
        }
    }
    public void T000_READ_BPTFCTR_UPD() throws IOException,SQLException,Exception {
        BPTFCTR_RD = new DBParm();
        BPTFCTR_RD.TableName = "BPTFCTR";
        BPTFCTR_RD.upd = true;
        IBS.READ(SCCGWA, BPRFCTR, BPTFCTR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
        }
    }
    public void T000_REWRITE_BPTFCTR() throws IOException,SQLException,Exception {
        BPTFCTR_RD = new DBParm();
        BPTFCTR_RD.TableName = "BPTFCTR";
        IBS.REWRITE(SCCGWA, BPRFCTR, BPTFCTR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_TBL_FLAG = 'N';
        } else {
        }
    }
    public void B031_TRANS_NHIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        IBS.init(SCCGWA, BPCNFCTR);
        IBS.init(SCCGWA, BPCOFCTR);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.CI_NO = BPRFCTR.CI_NO;
        BPCPNHIS.INFO.REF_NO = BPRFCTR.KEY.CTRT_NO;
        BPCPNHIS.INFO.TX_RMK = "FEE CONTRACT - UPD";
        BPCPNHIS.INFO.FMT_ID = "BPCHFCTR";
        BPCNFCTR.KEY.CTRT_NO = BPRFCTR.KEY.CTRT_NO;
        BPCNFCTR.CTRT_DESC = BPRFCTR.CTRT_DESC;
        BPCNFCTR.CI_NO = BPRFCTR.CI_NO;
        BPCNFCTR.CTRT_TYPE = BPRFCTR.CTRT_TYPE;
        BPCNFCTR.FEE_TYPE = BPRFCTR.FEE_TYPE;
        BPCNFCTR.BOOK_CENTRE = BPRFCTR.BOOK_CENTRE;
        BPCNFCTR.PRD_TYPE = BPRFCTR.PRD_TYPE;
        BPCNFCTR.START_DATE = BPRFCTR.START_DATE;
        BPCNFCTR.MATURITY_DATE = BPRFCTR.MATURITY_DATE;
        BPCNFCTR.HOLI_OVER = BPRFCTR.HOLI_OVER;
        BPCNFCTR.CAL_CODE1 = BPRFCTR.CAL_CODE1;
        BPCNFCTR.HOLI_METHOD = BPRFCTR.HOLI_METHOD;
        BPCNFCTR.CAL_CODE2 = BPRFCTR.CAL_CODE2;
        BPCNFCTR.PAY_IND = BPRFCTR.PAY_IND;
        BPCNFCTR.CASHFLOW_IND = BPRFCTR.CASHFLOW_IND;
        BPCNFCTR.BANK_PORTF = BPRFCTR.BANK_PORTF;
        BPCNFCTR.PAYMENT_METHOD = BPRFCTR.PAYMENT_METHOD;
        BPCNFCTR.ACCRUAL_TYPE = BPRFCTR.ACCRUAL_TYPE;
        BPCNFCTR.PRICE_METHOD = BPRFCTR.PRICE_METHOD;
        BPCNFCTR.TXN_CCY = BPRFCTR.TXN_CCY;
        BPCNFCTR.PRIN_AMT = BPRFCTR.PRIN_AMT;
        BPCNFCTR.REL_CTRT_NO = BPRFCTR.REL_CTRT_NO;
        BPCNFCTR.AMT_TYPE = BPRFCTR.AMT_TYPE;
        BPCNFCTR.REF_CCY = BPRFCTR.REF_CCY;
        BPCNFCTR.REF_METHOD = BPRFCTR.REF_METHOD;
        BPCNFCTR.CHARGE_CCY = BPRFCTR.CHARGE_CCY;
        BPCNFCTR.CHARGE_AMT = BPRFCTR.CHARGE_AMT;
        BPCNFCTR.CHARGE_METHOD = BPRFCTR.CHARGE_METHOD;
        BPCNFCTR.CR_TO_BR = BPRFCTR.CR_TO_BR;
        BPCNFCTR.CHARGE_AC = BPRFCTR.CHARGE_AC;
        BPCNFCTR.CHQ_NO = BPRFCTR.CHQ_NO;
        BPCNFCTR.NOSTRO_AC = BPRFCTR.NOSTRO_AC;
        BPCNFCTR.GL_MASTER1 = BPRFCTR.GL_MASTER1;
        BPCNFCTR.GL_MASTER2 = BPRFCTR.GL_MASTER2;
        BPCNFCTR.GL_MASTER3 = BPRFCTR.GL_MASTER3;
        BPCNFCTR.GL_MASTER4 = BPRFCTR.GL_MASTER4;
        BPCNFCTR.OIC_NO1 = "" + BPRFCTR.OIC_NO1;
        JIBS_tmp_int = BPCNFCTR.OIC_NO1.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCNFCTR.OIC_NO1 = "0" + BPCNFCTR.OIC_NO1;
        if (BPRFCTR.OIC_CENTRE1.trim().length() == 0) BPCNFCTR.OIC_CENTRE1 = 0;
        else BPCNFCTR.OIC_CENTRE1 = Integer.parseInt(BPRFCTR.OIC_CENTRE1);
        BPCNFCTR.OIC_PCT1 = BPRFCTR.OIC_PCT1;
        BPCNFCTR.OIC_NO2 = "" + BPRFCTR.OIC_NO2;
        JIBS_tmp_int = BPCNFCTR.OIC_NO2.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCNFCTR.OIC_NO2 = "0" + BPCNFCTR.OIC_NO2;
        if (BPRFCTR.OIC_CENTRE2.trim().length() == 0) BPCNFCTR.OIC_CENTRE2 = 0;
        else BPCNFCTR.OIC_CENTRE2 = Integer.parseInt(BPRFCTR.OIC_CENTRE2);
        BPCNFCTR.OIC_PCT2 = BPRFCTR.OIC_PCT2;
        BPCNFCTR.OIC_NO3 = "" + BPRFCTR.OIC_NO3;
        JIBS_tmp_int = BPCNFCTR.OIC_NO3.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCNFCTR.OIC_NO3 = "0" + BPCNFCTR.OIC_NO3;
        if (BPRFCTR.OIC_CENTRE3.trim().length() == 0) BPCNFCTR.OIC_CENTRE3 = 0;
        else BPCNFCTR.OIC_CENTRE3 = Integer.parseInt(BPRFCTR.OIC_CENTRE3);
        BPCNFCTR.OIC_PCT3 = BPRFCTR.OIC_PCT3;
        BPCNFCTR.FEE_STATUS = BPRFCTR.FEE_STATUS;
        BPCNFCTR.REMARK = BPRFCTR.REMARK;
        BPCNFCTR.FEE_BAS_AMT = BPRFCTR.FEE_BAS_AMT;
        BPCNFCTR.CHG_CCY_REAL = BPRFCTR.CHG_CCY_REAL;
        BPCNFCTR.CHG_AMT_REAL = BPRFCTR.CHG_AMT_REAL;
        BPCNFCTR.TICKET = BPRFCTR.TICKET;
        BPCNFCTR.RATE = BPRFCTR.RATE;
        BPCNFCTR.EXG_DATE = BPRFCTR.EXG_DATE;
        BPCNFCTR.EXG_TIME = BPRFCTR.EXG_TIME;
        BPCNFCTR.FCHG_MIN_AMT = BPRFCTR.FCHG_MIN_AMT;
        BPCPNHIS.INFO.FMT_ID_LEN = 1031;
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        IBS.CLONE(SCCGWA, BPCNFCTR, BPCOFCTR);
        BPCNFCTR.MATURITY_DATE = WS_MATURITY_DATE;
        BPCPNHIS.INFO.OLD_DAT_PT = BPCOFCTR;
        BPCPNHIS.INFO.NEW_DAT_PT = BPCNFCTR;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        WS_CTRT_NO = BPRFCTR.KEY.CTRT_NO;
        WS_CTRT_DESC = BPRFCTR.CTRT_DESC;
        WS_CI_NO = BPRFCTR.CI_NO;
        WS_CTRT_TYPE = BPRFCTR.CTRT_TYPE;
        WS_FEE_TYPE = BPRFCTR.FEE_TYPE;
        WS_BOOK_CENTRE = BPRFCTR.BOOK_CENTRE;
        WS_PRD_TYPE = BPRFCTR.PRD_TYPE;
        WS_START_DATE = BPRFCTR.START_DATE;
        WS_MATURITY_DATE = BPRFCTR.MATURITY_DATE;
        WS_HOLI_OVER = BPRFCTR.HOLI_OVER;
        WS_CAL_CODE1 = BPRFCTR.CAL_CODE1;
        WS_HOLI_METHOD = BPRFCTR.HOLI_METHOD;
        WS_CAL_CODE2 = BPRFCTR.CAL_CODE2;
        WS_PAY_IND = BPRFCTR.PAY_IND;
        WS_CASHFLOW_IND = BPRFCTR.CASHFLOW_IND;
        WS_BANK_PORTF = BPRFCTR.BANK_PORTF;
        WS_PAYMENT_METHOD = BPRFCTR.PAYMENT_METHOD;
        WS_ACCRUAL_TYPE = BPRFCTR.ACCRUAL_TYPE;
        WS_PRICE_METHOD = BPRFCTR.PRICE_METHOD;
        WS_TXN_CCY = BPRFCTR.TXN_CCY;
        WS_PRIN_AMT = BPRFCTR.PRIN_AMT;
        WS_REL_CTRT_NO = BPRFCTR.REL_CTRT_NO;
        WS_AMT_TYPE = BPRFCTR.AMT_TYPE;
        WS_AGGR_TYPE = BPRFCTR.AGGR_TYPE;
        WS_REF_CCY = BPRFCTR.REF_CCY;
        WS_REF_METHOD = BPRFCTR.REF_METHOD;
        WS_CHARGE_CCY = BPRFCTR.CHARGE_CCY;
        WS_CHARGE_AMT = BPRFCTR.CHARGE_AMT;
        WS_CHARGE_METHOD = BPRFCTR.CHARGE_METHOD;
        WS_CR_TO_BR = BPRFCTR.CR_TO_BR;
        WS_CHARGE_AC = BPRFCTR.CHARGE_AC;
        WS_CHQ_NO = BPRFCTR.CHQ_NO;
        WS_NOSTRO_AC = BPRFCTR.NOSTRO_AC;
        WS_CARD_PSBK_NO = BPRFCTR.CARD_PSBK_NO;
        WS_SALE_DATE = BPRFCTR.SALE_DATE;
        WS_CCY_TYPE = BPRFCTR.CCY_TYPE;
        WS_GL_MASTER1 = BPRFCTR.GL_MASTER1;
        WS_GL_MASTER2 = BPRFCTR.GL_MASTER2;
        WS_GL_MASTER3 = BPRFCTR.GL_MASTER3;
        WS_GL_MASTER4 = BPRFCTR.GL_MASTER4;
        WS_OIC_NO1 = "" + BPRFCTR.OIC_NO1;
        JIBS_tmp_int = WS_OIC_NO1.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) WS_OIC_NO1 = "0" + WS_OIC_NO1;
        if (BPRFCTR.OIC_CENTRE1.trim().length() == 0) WS_OIC_CENTRE1 = 0;
        else WS_OIC_CENTRE1 = Integer.parseInt(BPRFCTR.OIC_CENTRE1);
        WS_OIC_PCT1 = BPRFCTR.OIC_PCT1;
        WS_OIC_NO2 = "" + BPRFCTR.OIC_NO2;
        JIBS_tmp_int = WS_OIC_NO2.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) WS_OIC_NO2 = "0" + WS_OIC_NO2;
        if (BPRFCTR.OIC_CENTRE2.trim().length() == 0) WS_OIC_CENTRE2 = 0;
        else WS_OIC_CENTRE2 = Integer.parseInt(BPRFCTR.OIC_CENTRE2);
        WS_OIC_PCT2 = BPRFCTR.OIC_PCT2;
        WS_OIC_NO3 = "" + BPRFCTR.OIC_NO3;
        JIBS_tmp_int = WS_OIC_NO3.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) WS_OIC_NO3 = "0" + WS_OIC_NO3;
        if (BPRFCTR.OIC_CENTRE3.trim().length() == 0) WS_OIC_CENTRE3 = 0;
        else WS_OIC_CENTRE3 = Integer.parseInt(BPRFCTR.OIC_CENTRE3);
        WS_OIC_PCT3 = BPRFCTR.OIC_PCT3;
        WS_FEE_STATUS = BPRFCTR.FEE_STATUS;
        WS_REMARK = BPRFCTR.REMARK;
        WS_FEE_BAS_AMT = BPRFCTR.FEE_BAS_AMT;
        WS_CHG_CCY_REAL = BPRFCTR.CHG_CCY_REAL;
        WS_CHG_AMT_REAL = BPRFCTR.CHG_AMT_REAL;
        WS_TICKET = BPRFCTR.TICKET;
        WS_RATE = BPRFCTR.RATE;
        WS_EXG_DATE = BPRFCTR.EXG_DATE;
        WS_EXG_TIME = BPRFCTR.EXG_TIME;
        WS_FCHG_MIN_AMT = BPRFCTR.FCHG_MIN_AMT;
        WS_JNL_NO = BPRFCTR.JNL_NO;
        WS_REAL_AC_DATE = BPRFCTR.REAL_AC_DATE;
        WS_VOUCHER_NO = BPRFCTR.VOUCHER_NO;
        WS_CREATE_DATE = BPRFCTR.CREATE_DATE;
        WS_CREATE_TELL = BPRFCTR.CREATE_TELL;
        WS_UPDTBL_DATE = BPRFCTR.UPDTBL_DATE;
        WS_LAST_TELL = BPRFCTR.LAST_TELL;
        WS_SUP_TEL1 = BPRFCTR.SUP_TEL1;
        WS_SUP_TEL2 = BPRFCTR.SUP_TEL2;
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCSFECT.CTNO = WS_CTRT_NO;
        BPCSFECT.CT_TYP = WS_CTRT_TYPE;
        BPCSFECT.FEE_TYP = WS_FEE_TYPE;
        BPCSFECT.BOOK_ACCT = WS_BOOK_CENTRE;
        BPCSFECT.PRDT_TYP = WS_PRD_TYPE;
        BPCSFECT.START_DT = WS_START_DATE;
        BPCSFECT.MATURITY_DT = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, WS_START_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, BPCSFECT.MATURITY_DT);
        BPCSFECT.HOL_OVR = WS_HOLI_OVER;
        BPCSFECT.CAL_CD1 = WS_CAL_CODE1;
        BPCSFECT.CAL_CD2 = WS_CAL_CODE2;
        BPCSFECT.HOL_METH = WS_HOLI_METHOD;
        BPCSFECT.PAY_IND = WS_PAY_IND;
        BPCSFECT.CSH_FLW_IND = WS_CASHFLOW_IND;
        BPCSFECT.BK_PFLO = WS_BANK_PORTF;
        BPCSFECT.PAY_METH = WS_PAYMENT_METHOD;
        BPCSFECT.ACU_TYP = WS_ACCRUAL_TYPE;
        BPCSFECT.FIX_PRC_METH = WS_PRICE_METHOD;
        BPCSFECT.TXN_CCY = WS_TXN_CCY;
        BPCSFECT.TXN_AMT = WS_PRIN_AMT;
        BPCSFECT.RLTD_CTNO = WS_REL_CTRT_NO;
        BPCSFECT.AMT_TYP = WS_AMT_TYPE;
        BPCSFECT.AGGR_TYP = WS_AGGR_TYPE;
        BPCSFECT.REF_CCY = WS_REF_CCY;
        BPCSFECT.REF_METH = WS_REF_METHOD;
        BPCSFECT.CHG_CCY = WS_CHARGE_CCY;
        BPCSFECT.CHG_AMT = WS_CHARGE_AMT;
        BPCSFECT.CHG_METH = WS_CHARGE_METHOD;
        BPCSFECT.CHG_ACNO = WS_CHARGE_AC;
        BPCSFECT.CINO = WS_CI_NO;
        BPCSFECT.NOSTRO_CD = WS_NOSTRO_AC;
        BPCSFECT.CHQ_NO = WS_CHQ_NO;
        BPCSFECT.GL_MST_BR = WS_GL_MASTER1;
        BPCSFECT.GL_MST_HO = WS_GL_MASTER2;
        BPCSFECT.GL_MST_IAS39 = WS_GL_MASTER3;
        BPCSFECT.GL_MST_UNUS = WS_GL_MASTER4;
        BPCSFECT.TXN_OIC_DATA[1-1].TXN_OIC = WS_OIC_NO1;
        BPCSFECT.TXN_OIC_DATA[1-1].OIC_ACCT = WS_OIC_CENTRE1;
        BPCSFECT.TXN_OIC_DATA[1-1].PRFT_PCT = WS_OIC_PCT1;
        BPCSFECT.TXN_OIC_DATA[2-1].TXN_OIC = WS_OIC_NO2;
        BPCSFECT.TXN_OIC_DATA[2-1].OIC_ACCT = WS_OIC_CENTRE2;
        BPCSFECT.TXN_OIC_DATA[2-1].PRFT_PCT = WS_OIC_PCT2;
        BPCSFECT.TXN_OIC_DATA[3-1].TXN_OIC = WS_OIC_NO3;
        BPCSFECT.TXN_OIC_DATA[3-1].OIC_ACCT = WS_OIC_CENTRE3;
        BPCSFECT.TXN_OIC_DATA[3-1].PRFT_PCT = WS_OIC_PCT3;
        if (BPCSFECT.PAY_METH == 'P') {
            if (BPCSFECT.START_DT > SCCGWA.COMM_AREA.AC_DATE) {
                BPCSFECT.CT_STS = '0';
            } else {
                if (BPCSFECT.MATURITY_DT != BPCSFECT.START_DT) {
                    BPCSFECT.CT_STS = '1';
                } else {
                    BPCSFECT.CT_STS = '2';
                }
            }
        } else {
            BPCSFECT.CT_STS = '0';
        }
        BPCSFECT.REMARK = WS_REMARK;
        BPCSFECT.FEE_BAS_AMT = WS_FEE_BAS_AMT;
        BPCSFECT.CHG_CCY_REAL = WS_CHG_CCY_REAL;
        BPCSFECT.CHG_AMT_REAL = WS_CHG_AMT_REAL;
        BPCSFECT.TICKET = WS_TICKET;
        BPCSFECT.RATE = WS_RATE;
        BPCSFECT.EXG_DATE = WS_EXG_DATE;
        BPCSFECT.EXG_TIME = WS_EXG_TIME;
        BPCSFECT.MIN_AMT = WS_FCHG_MIN_AMT;
        if (WS_CHARGE_METHOD == '9') {
            BPCSFECT.CHG_FLG = 'N';
        } else {
            BPCSFECT.CHG_FLG = 'Y';
        }
        BPCSFECT.CP_NO = WS_CARD_PSBK_NO;
        BPCSFECT.SALE_DT = WS_SALE_DATE;
        BPCSFECT.CCY_TYPE = WS_CCY_TYPE;
        BPCSFECT.FEE_DESC = WS_CTRT_DESC;
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_CALL_BPZSFECT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-FECT-MAINT", BPCSFECT);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
