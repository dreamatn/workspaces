package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT9122 {
    String JIBS_tmp_str[] = new String[10];
    BPOT9122_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPOT9122_WS_TEMP_VARIABLE();
    char WS_SEQ_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    BPCSFECT BPCSFECT = new BPCSFECT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPCPORUP_DATA_INFO BPCPORUP;
    BPB9122_AWA_9122 BPB9122_AWA_9122;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT9122 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB9122_AWA_9122>");
        BPB9122_AWA_9122 = (BPB9122_AWA_9122) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        IBS.init(SCCGWA, WS_TEMP_VARIABLE);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_ADD_FEE_CNTR_PROC();
        BPB9122_AWA_9122.CTNO = BPCSFECT.CTNO;
        CEP.TRC(SCCGWA, BPB9122_AWA_9122.CTNO);
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB9122_AWA_9122.BOOK_BR != 0) {
            CEP.TRC(SCCGWA, BPB9122_AWA_9122.BOOK_BR);
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = BPB9122_AWA_9122.BOOK_BR;
            S000_CALL_BPZPQORG();
            CEP.TRC(SCCGWA, BPCPQORG.ATTR);
            if (BPCPQORG.ATTR != '2') {
                CEP.TRC(SCCGWA, "111");
                CEP.TRC(SCCGWA, BPCMSG_ERROR_MSG.BP_BR_TYP_WORNG);
                WS_TEMP_VARIABLE.WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_TYP_WORNG;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        CEP.TRC(SCCGWA, BPB9122_AWA_9122.RELT_FLG);
        if (BPB9122_AWA_9122.RELT_FLG != ' ') {
            if (BPB9122_AWA_9122.RELT_FLG != '0' 
                && BPB9122_AWA_9122.RELT_FLG != '1') {
                WS_TEMP_VARIABLE.WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        } else {
            WS_TEMP_VARIABLE.WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RELT_MUST_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void B200_ADD_FEE_CNTR_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB9122_AWA_9122.CTNO);
        CEP.TRC(SCCGWA, BPB9122_AWA_9122.FEE_DESC);
        CEP.TRC(SCCGWA, BPB9122_AWA_9122.CINO);
        CEP.TRC(SCCGWA, BPB9122_AWA_9122.CI_CNM);
        CEP.TRC(SCCGWA, BPB9122_AWA_9122.CT_TYP);
        CEP.TRC(SCCGWA, BPB9122_AWA_9122.FEE_TYP);
        CEP.TRC(SCCGWA, BPB9122_AWA_9122.BOOK_BR);
        CEP.TRC(SCCGWA, BPB9122_AWA_9122.PRDT_TYP);
        CEP.TRC(SCCGWA, BPB9122_AWA_9122.START_DT);
        CEP.TRC(SCCGWA, BPB9122_AWA_9122.END_DT);
        CEP.TRC(SCCGWA, BPB9122_AWA_9122.HOL_OVR);
        CEP.TRC(SCCGWA, BPB9122_AWA_9122.CAL_CD1);
        CEP.TRC(SCCGWA, BPB9122_AWA_9122.HOL_METH);
        CEP.TRC(SCCGWA, BPB9122_AWA_9122.CAL_CD2);
        CEP.TRC(SCCGWA, BPB9122_AWA_9122.PAY_IND);
        CEP.TRC(SCCGWA, BPB9122_AWA_9122.CSH_FLW);
        CEP.TRC(SCCGWA, BPB9122_AWA_9122.BK_PFLO);
        CEP.TRC(SCCGWA, BPB9122_AWA_9122.PAY_METH);
        CEP.TRC(SCCGWA, BPB9122_AWA_9122.ACU_TYP);
        CEP.TRC(SCCGWA, BPB9122_AWA_9122.FEE_METH);
        CEP.TRC(SCCGWA, BPB9122_AWA_9122.TXN_CCY);
        CEP.TRC(SCCGWA, BPB9122_AWA_9122.TXN_AMT);
        CEP.TRC(SCCGWA, BPB9122_AWA_9122.RLT_CTNO);
        CEP.TRC(SCCGWA, BPB9122_AWA_9122.RLT_CTYP);
        CEP.TRC(SCCGWA, BPB9122_AWA_9122.AMT_TYP);
        CEP.TRC(SCCGWA, BPB9122_AWA_9122.RLT_PTYP);
        CEP.TRC(SCCGWA, BPB9122_AWA_9122.AMT_PCT);
        CEP.TRC(SCCGWA, BPB9122_AWA_9122.FBAS_CNT);
        CEP.TRC(SCCGWA, BPB9122_AWA_9122.AGGR_TYP);
        CEP.TRC(SCCGWA, BPB9122_AWA_9122.REF_CCY);
        CEP.TRC(SCCGWA, BPB9122_AWA_9122.REF_METH);
        CEP.TRC(SCCGWA, BPB9122_AWA_9122.CHG_CCY);
        CEP.TRC(SCCGWA, BPB9122_AWA_9122.CHG_AMT);
        CEP.TRC(SCCGWA, BPB9122_AWA_9122.CHG_METH);
        CEP.TRC(SCCGWA, BPB9122_AWA_9122.CHG_ACNO);
        CEP.TRC(SCCGWA, BPB9122_AWA_9122.NOSAC_CD);
        CEP.TRC(SCCGWA, BPB9122_AWA_9122.CHQ_NO);
        CEP.TRC(SCCGWA, BPB9122_AWA_9122.GLMST_BR);
        CEP.TRC(SCCGWA, BPB9122_AWA_9122.GLMST_HO);
        CEP.TRC(SCCGWA, BPB9122_AWA_9122.GLMST_IA);
        CEP.TRC(SCCGWA, BPB9122_AWA_9122.GLMST_UN);
        CEP.TRC(SCCGWA, BPB9122_AWA_9122.CT_STS);
        CEP.TRC(SCCGWA, BPB9122_AWA_9122.REMARK);
        CEP.TRC(SCCGWA, BPB9122_AWA_9122.CP_NO);
        CEP.TRC(SCCGWA, BPB9122_AWA_9122.SALE_DT);
        CEP.TRC(SCCGWA, BPB9122_AWA_9122.CCY_TYPE);
        CEP.TRC(SCCGWA, BPB9122_AWA_9122.CHG_CCYR);
        CEP.TRC(SCCGWA, BPB9122_AWA_9122.CHG_AMTR);
        CEP.TRC(SCCGWA, BPB9122_AWA_9122.CHG_FLG);
        CEP.TRC(SCCGWA, BPB9122_AWA_9122.CREV_NO);
        IBS.init(SCCGWA, BPCSFECT);
        BPCSFECT.FUNC_CODE = 'A';
        R000_MOVE_DATA();
        S000_CALL_BPZSFECT();
    }
    public void R000_MOVE_DATA() throws IOException,SQLException,Exception {
        BPCSFECT.CTNO = BPB9122_AWA_9122.CTNO;
        BPCSFECT.FEE_DESC = BPB9122_AWA_9122.FEE_DESC;
        BPCSFECT.CINO = BPB9122_AWA_9122.CINO;
        BPCSFECT.CI_ABBR_NM = BPB9122_AWA_9122.CI_CNM;
        BPCSFECT.CT_TYP = BPB9122_AWA_9122.CT_TYP;
        BPCSFECT.FEE_TYP = BPB9122_AWA_9122.FEE_TYP;
        BPCSFECT.BOOK_ACCT = BPB9122_AWA_9122.BOOK_BR;
        BPCSFECT.PRDT_TYP = BPB9122_AWA_9122.PRDT_TYP;
        BPCSFECT.START_DT = BPB9122_AWA_9122.START_DT;
        BPCSFECT.MATURITY_DT = BPB9122_AWA_9122.END_DT;
        BPCSFECT.HOL_OVR = BPB9122_AWA_9122.HOL_OVR;
        BPCSFECT.HOL_METH = BPB9122_AWA_9122.HOL_METH;
        BPCSFECT.CAL_CD1 = BPB9122_AWA_9122.CAL_CD1;
        BPCSFECT.CAL_CD2 = BPB9122_AWA_9122.CAL_CD2;
        BPCSFECT.PAY_IND = BPB9122_AWA_9122.PAY_IND;
        BPCSFECT.CSH_FLW_IND = BPB9122_AWA_9122.CSH_FLW;
        BPCSFECT.BK_PFLO = BPB9122_AWA_9122.BK_PFLO;
        BPCSFECT.PAY_METH = BPB9122_AWA_9122.PAY_METH;
        BPCSFECT.ACU_TYP = BPB9122_AWA_9122.ACU_TYP;
        BPCSFECT.TXN_CCY = BPB9122_AWA_9122.TXN_CCY;
        BPCSFECT.TXN_AMT = BPB9122_AWA_9122.TXN_AMT;
        BPCSFECT.RLTD_CTNO = BPB9122_AWA_9122.RLT_CTNO;
        BPCSFECT.RLTD_CT_TYP = BPB9122_AWA_9122.RLT_CTYP;
        BPCSFECT.AMT_TYP = BPB9122_AWA_9122.AMT_TYP;
        BPCSFECT.RLTD_PRDT_TYP = BPB9122_AWA_9122.RLT_PTYP;
        BPCSFECT.AMT_PCT = BPB9122_AWA_9122.AMT_PCT;
        BPCSFECT.INT_BASIC = BPB9122_AWA_9122.FBAS_CNT;
        BPCSFECT.AGGR_TYP = BPB9122_AWA_9122.AGGR_TYP;
        BPCSFECT.REF_CCY = BPB9122_AWA_9122.REF_CCY;
        BPCSFECT.REF_METH = BPB9122_AWA_9122.REF_METH;
        for (WS_TEMP_VARIABLE.WS_I = 1; WS_TEMP_VARIABLE.WS_I <= 5; WS_TEMP_VARIABLE.WS_I += 1) {
            CEP.TRC(SCCGWA, BPB9122_AWA_9122.REF_FEED[WS_TEMP_VARIABLE.WS_I-1].REF_AMT);
            CEP.TRC(SCCGWA, BPB9122_AWA_9122.REF_FEED[WS_TEMP_VARIABLE.WS_I-1].REF_PCT);
            CEP.TRC(SCCGWA, BPB9122_AWA_9122.REF_FEED[WS_TEMP_VARIABLE.WS_I-1].FEE_AMT);
            CEP.TRC(SCCGWA, BPB9122_AWA_9122.REF_FEED[WS_TEMP_VARIABLE.WS_I-1].FEE_RATE);
            CEP.TRC(SCCGWA, BPB9122_AWA_9122.REF_FEED[WS_TEMP_VARIABLE.WS_I-1].AGG_MTH);
            BPCSFECT.REF_FEE_DATA[WS_TEMP_VARIABLE.WS_I-1].REF_AMT = BPB9122_AWA_9122.REF_FEED[WS_TEMP_VARIABLE.WS_I-1].REF_AMT;
            BPCSFECT.REF_FEE_DATA[WS_TEMP_VARIABLE.WS_I-1].REF_PCT = BPB9122_AWA_9122.REF_FEED[WS_TEMP_VARIABLE.WS_I-1].REF_PCT;
            BPCSFECT.REF_FEE_DATA[WS_TEMP_VARIABLE.WS_I-1].AGG_MTH = BPB9122_AWA_9122.REF_FEED[WS_TEMP_VARIABLE.WS_I-1].AGG_MTH;
            BPCSFECT.REF_FEE_DATA[WS_TEMP_VARIABLE.WS_I-1].FEE_AMT = BPB9122_AWA_9122.REF_FEED[WS_TEMP_VARIABLE.WS_I-1].FEE_AMT;
            BPCSFECT.REF_FEE_DATA[WS_TEMP_VARIABLE.WS_I-1].FEE_RATE = BPB9122_AWA_9122.REF_FEED[WS_TEMP_VARIABLE.WS_I-1].FEE_RATE;
        }
        BPCSFECT.CHG_CCY = BPB9122_AWA_9122.CHG_CCY;
        BPCSFECT.CHG_AMT = BPB9122_AWA_9122.CHG_AMT;
        BPCSFECT.CHG_METH = BPB9122_AWA_9122.CHG_METH;
        BPCSFECT.CHG_ACNO = BPB9122_AWA_9122.CHG_ACNO;
        BPCSFECT.CHQ_NO = BPB9122_AWA_9122.CHQ_NO;
        BPCSFECT.GL_MST_BR = 0;
        BPCSFECT.GL_MST_HO = 0;
        BPCSFECT.GL_MST_IAS39 = 0;
        BPCSFECT.GL_MST_UNUS = 0;
        for (WS_TEMP_VARIABLE.WS_J = 1; WS_TEMP_VARIABLE.WS_J <= 5; WS_TEMP_VARIABLE.WS_J += 1) {
            BPCSFECT.TXN_OIC_DATA[WS_TEMP_VARIABLE.WS_J-1].TXN_OIC = BPB9122_AWA_9122.TXN_OICD[WS_TEMP_VARIABLE.WS_J-1].TXN_OIC;
            BPCSFECT.TXN_OIC_DATA[WS_TEMP_VARIABLE.WS_J-1].OIC_ACCT = BPB9122_AWA_9122.TXN_OICD[WS_TEMP_VARIABLE.WS_J-1].OIC_ACCT;
            BPCSFECT.TXN_OIC_DATA[WS_TEMP_VARIABLE.WS_J-1].PRFT_PCT = BPB9122_AWA_9122.TXN_OICD[WS_TEMP_VARIABLE.WS_J-1].PRFT_PCT;
        }
        BPCSFECT.RELT_FLG = BPB9122_AWA_9122.RELT_FLG;
        CEP.TRC(SCCGWA, BPCSFECT.RELT_FLG);
        if (BPCSFECT.PAY_METH == 'P') {
            if (BPCSFECT.RELT_FLG == '1') {
                CEP.TRC(SCCGWA, "FEE CONTRACT STATUS:UNSETTLE");
                BPCSFECT.CT_STS = '0';
            } else {
                if (BPCSFECT.MATURITY_DT != BPCSFECT.START_DT) {
                    CEP.TRC(SCCGWA, "FEE CONTRACT STATUS:SETTLED");
                    BPCSFECT.CT_STS = '1';
                } else {
                    CEP.TRC(SCCGWA, "FEE CONTRACT STATUS:COMPLETE");
                    BPCSFECT.CT_STS = '2';
                }
            }
        } else {
            BPCSFECT.CT_STS = '0';
        }
        BPCSFECT.REMARK = BPB9122_AWA_9122.REMARK;
        BPCSFECT.FEE_BAS_AMT = BPB9122_AWA_9122.FEEB_AMT;
        CEP.TRC(SCCGWA, BPB9122_AWA_9122.FEEB_AMT);
        BPCSFECT.CHG_CCY_REAL = BPB9122_AWA_9122.CHG_CCY;
        BPCSFECT.CHG_AMT_REAL = BPB9122_AWA_9122.CHG_AMT;
        CEP.TRC(SCCGWA, BPCSFECT.CHG_CCY_REAL);
        CEP.TRC(SCCGWA, BPCSFECT.CHG_AMT_REAL);
        BPCSFECT.TICKET = BPB9122_AWA_9122.TICKET;
        BPCSFECT.RATE = BPB9122_AWA_9122.RATE;
        BPCSFECT.EXG_DATE = BPB9122_AWA_9122.EXG_DATE;
        BPCSFECT.EXG_TIME = BPB9122_AWA_9122.EXG_TIME;
        BPCSFECT.MIN_AMT = BPB9122_AWA_9122.MIN_AMT;
        BPCSFECT.CP_NO = BPB9122_AWA_9122.CP_NO;
        BPCSFECT.SALE_DT = BPB9122_AWA_9122.SALE_DT;
        BPCSFECT.CCY_TYPE = BPB9122_AWA_9122.CCY_TYPE;
        BPCSFECT.CREV_NO = BPB9122_AWA_9122.CREV_NO;
    }
    public void S000_CALL_BPZSFECT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-FECT-MAINT", BPCSFECT);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        CEP.TRC(SCCGWA, BPCPQORG.RC);
        CEP.TRC(SCCGWA, BPCPQORG.RC.RC_CODE);
        if (BPCPQORG.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "1111");
            WS_TEMP_VARIABLE.WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_ERR_MSG);
        CEP.ERRC(SCCGWA, WS_TEMP_VARIABLE.WS_ERR_MSG, WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}