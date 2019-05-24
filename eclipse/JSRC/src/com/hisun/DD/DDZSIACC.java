package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.DC.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSIACC {
    DDCOIACC_CHQ_DETL CHQ_DETL;
    int JIBS_tmp_int;
    DBParm DDTMST_RD;
    brParm DDTCCY_BR = new brParm();
    brParm DDTCHQ_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    DBParm DDTCCY_RD;
    DBParm DDTCHQ_RD;
    String K_FMT_CHQ = "DD812";
    String WS_ERR_MSG = " ";
    String WS_CHQ_NO = " ";
    short WS_CHQ_CNT = 0;
    short WS_CNT = 0;
    long WS_STR_CHQ_NO = 0;
    long WS_END_CHQ_NO = 0;
    short WS_NOR_CNT = 0;
    short WS_PAID_CNT = 0;
    short WS_STOP_CNT = 0;
    short WS_DISCARD_CNT = 0;
    short WS_NOR_TCNT = 0;
    short WS_PAID_TCNT = 0;
    short WS_STOP_TCNT = 0;
    short WS_DISCARD_TCNT = 0;
    short WS_INV_TCNT = 0;
    short WS_L_TCNT = 0;
    String WS_CHQ_STS = " ";
    long WS_SCHQ_NO = 0;
    long WS_ECHQ_NO = 0;
    int WS_LEN = 0;
    char WS_CHQ_FLG = ' ';
    char WS_DDTCCY_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    DDCOIACC DDCOIACC = new DDCOIACC();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DDRCCY DDRCCY = new DDRCCY();
    DDRCHQ DDRCHQ = new DDRCHQ();
    DDRMST DDRMST = new DDRMST();
    CICQACRI CICQACRI = new CICQACRI();
    CICQACAC CICQACAC = new CICQACAC();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    DDCSIACC DDCSIACC;
    public void MP(SCCGWA SCCGWA, DDCSIACC DDCSIACC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSIACC = DDCSIACC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDZSIACC return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        R000_CHK_OUTPUT_AC();
        B030_GET_AC_INF();
        B040_GET_ACO_AC();
        B050_CHK_AC_STS();
        CEP.TRC(SCCGWA, DDCSIACC.FUNC);
        if (DDCSIACC.FUNC == 'I') {
            B070_GET_AC_UNUSE_CHQ();
            B090_FMT_OUTPUT();
        } else if (DDCSIACC.FUNC == 'D') {
            B110_DSD_AC_UNUSE_CHQ();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + DDCSIACC.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSIACC.AC);
        CEP.TRC(SCCGWA, DDCSIACC.CHQ_NO);
        CEP.TRC(SCCGWA, DDCSIACC.CHQ_STS);
        if (DDCSIACC.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_GET_AC_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCSIACC.AC;
        T000_READ_DDTMST();
    }
    public void B040_GET_ACO_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = DDCSIACC.AC;
        S000_CALL_CIZQACAC();
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_READ_DDTCCY_PROC();
    }
    public void B050_CHK_AC_STS() throws IOException,SQLException,Exception {
        if (DDRMST.AC_STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
        }
    }
    public void B070_GET_AC_UNUSE_CHQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCHQ);
        DDRCHQ.KEY.AC = DDRCCY.KEY.AC;
        WS_NOR_TCNT = 0;
        WS_PAID_TCNT = 0;
        WS_STOP_TCNT = 0;
        WS_DISCARD_TCNT = 0;
        T000_STARTBR_DDTCHQ();
        T000_READNEXT_DDTCHQ();
        if (WS_CHQ_FLG == 'N') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQB_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, DDCOIACC.BASIC_INFO);
        DDCOIACC.BASIC_INFO.AC_NO = DDCSIACC.AC;
        DDCOIACC.BASIC_INFO.CCY = DDRCHQ.CCY;
        DDCOIACC.BASIC_INFO.CHQ_CNT = 0;
        CHQ_DETL = new DDCOIACC_CHQ_DETL();
        DDCOIACC.CHQ_DETL.add(CHQ_DETL);
        while (WS_CHQ_FLG != 'N') {
            DDCOIACC.BASIC_INFO.CHQ_CNT += 1;
            CHQ_DETL = new DDCOIACC_CHQ_DETL();
            DDCOIACC.CHQ_DETL.add(CHQ_DETL);
            IBS.init(SCCGWA, CHQ_DETL);
            CHQ_DETL.CHQ_TYP = DDRCHQ.KEY.CHQ_TYP;
            CHQ_DETL.START_NO = DDRCHQ.KEY.STR_CHQ_NO;
            CHQ_DETL.END_NO = DDRCHQ.KEY.END_CHQ_NO;
            if (DDRCHQ.KEY.STR_CHQ_NO.trim().length() == 0) WS_STR_CHQ_NO = 0;
            else WS_STR_CHQ_NO = Long.parseLong(DDRCHQ.KEY.STR_CHQ_NO);
            if (DDRCHQ.KEY.END_CHQ_NO.trim().length() == 0) WS_END_CHQ_NO = 0;
            else WS_END_CHQ_NO = Long.parseLong(DDRCHQ.KEY.END_CHQ_NO);
            WS_NOR_CNT = 0;
            WS_PAID_CNT = 0;
            WS_STOP_CNT = 0;
            WS_DISCARD_CNT = 0;
            WS_CHQ_STS = " ";
            WS_CHQ_CNT = (short) (WS_END_CHQ_NO - WS_STR_CHQ_NO + 1);
            for (WS_CNT = 1; WS_CNT <= WS_CHQ_CNT; WS_CNT += 1) {
                if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
                JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
                if (DDRCHQ.CHQ_STS.substring(WS_CNT - 1, WS_CNT + 1 - 1).equalsIgnoreCase("1")) {
                    WS_NOR_CNT += 1;
                } else {
                    if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
                    JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
                    for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
                    if (DDRCHQ.CHQ_STS.substring(WS_CNT - 1, WS_CNT + 1 - 1).equalsIgnoreCase("2")) {
                        WS_PAID_CNT += 1;
                    } else {
                        if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
                        JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
                        for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
                        if (DDRCHQ.CHQ_STS.substring(WS_CNT - 1, WS_CNT + 1 - 1).equalsIgnoreCase("4")) {
                            WS_STOP_CNT += 1;
                        } else {
                            WS_DISCARD_CNT += 1;
                        }
                    }
                }
            }
            if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
            JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
            for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
            if (WS_CHQ_STS == null) WS_CHQ_STS = "";
            JIBS_tmp_int = WS_CHQ_STS.length();
            for (int i=0;i<50-JIBS_tmp_int;i++) WS_CHQ_STS += " ";
            WS_CHQ_STS = DDRCHQ.CHQ_STS.substring(0, WS_CHQ_CNT) + WS_CHQ_STS.substring(WS_CHQ_CNT);
            CHQ_DETL.NOR_NUM = WS_NOR_CNT;
            CHQ_DETL.PAID_NUM = WS_PAID_CNT;
            CHQ_DETL.STOP_NUM = WS_STOP_CNT;
            CHQ_DETL.DISCARD_NUM = WS_DISCARD_CNT;
            CHQ_DETL.CHQ_STS = WS_CHQ_STS;
            T000_READNEXT_DDTCHQ();
        }
        CEP.TRC(SCCGWA, DDCOIACC);
        CEP.TRC(SCCGWA, WS_NOR_TCNT);
        CEP.TRC(SCCGWA, WS_PAID_TCNT);
        CEP.TRC(SCCGWA, WS_STOP_TCNT);
        CEP.TRC(SCCGWA, WS_DISCARD_TCNT);
        T000_ENDBR_DDTCHQ();
    }
    public void B090_FMT_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_FMT_CHQ;
        SCCFMT.DATA_PTR = DDCOIACC;
        SCCFMT.DATA_LEN = 98938;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B110_DSD_AC_UNUSE_CHQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCHQ);
        DDRCHQ.KEY.AC = DDRCCY.KEY.AC;
        WS_INV_TCNT = 0;
        WS_L_TCNT = 0;
        T000_STARTBR_DDTCHQ();
        T000_READNEXT_DDTCHQ();
        if (WS_CHQ_FLG == 'N') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQB_REC_NOTFND;
            CEP.TRC(SCCGWA, DDCMSG_ERROR_MSG.DD_CHQB_REC_NOTFND);
        }
        while (WS_CHQ_FLG != 'N') {
            WS_LEN = 0;
            WS_LEN = DDRCHQ.KEY.STR_CHQ_NO.trim().length();
            CEP.TRC(SCCGWA, WS_LEN);
            if (DDRCHQ.KEY.STR_CHQ_NO == null) DDRCHQ.KEY.STR_CHQ_NO = "";
            JIBS_tmp_int = DDRCHQ.KEY.STR_CHQ_NO.length();
            for (int i=0;i<16-JIBS_tmp_int;i++) DDRCHQ.KEY.STR_CHQ_NO += " ";
            if (DDRCHQ.KEY.STR_CHQ_NO.substring(0, WS_LEN).trim().length() == 0) WS_STR_CHQ_NO = 0;
            else WS_STR_CHQ_NO = Long.parseLong(DDRCHQ.KEY.STR_CHQ_NO.substring(0, WS_LEN));
            if (DDRCHQ.KEY.END_CHQ_NO == null) DDRCHQ.KEY.END_CHQ_NO = "";
            JIBS_tmp_int = DDRCHQ.KEY.END_CHQ_NO.length();
            for (int i=0;i<16-JIBS_tmp_int;i++) DDRCHQ.KEY.END_CHQ_NO += " ";
            if (DDRCHQ.KEY.END_CHQ_NO.substring(0, WS_LEN).trim().length() == 0) WS_END_CHQ_NO = 0;
            else WS_END_CHQ_NO = Long.parseLong(DDRCHQ.KEY.END_CHQ_NO.substring(0, WS_LEN));
            WS_CHQ_CNT = (short) (WS_END_CHQ_NO - WS_STR_CHQ_NO + 1);
            for (WS_CNT = 1; WS_CNT <= WS_CHQ_CNT; WS_CNT += 1) {
                if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
                JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
                if (!DDRCHQ.CHQ_STS.substring(WS_CNT - 1, WS_CNT + 1 - 1).equalsIgnoreCase("2") 
                    || !DDRCHQ.CHQ_STS.substring(WS_CNT - 1, WS_CNT + 1 - 1).equalsIgnoreCase("5")) {
                    if (WS_CHQ_STS == null) WS_CHQ_STS = "";
                    JIBS_tmp_int = WS_CHQ_STS.length();
                    for (int i=0;i<50-JIBS_tmp_int;i++) WS_CHQ_STS += " ";
                    WS_CHQ_STS = WS_CHQ_STS.substring(0, WS_CNT - 1) + "5" + WS_CHQ_STS.substring(WS_CNT + 1 - 1);
                }
            }
            T000_READUP_DDTCHQ();
            DDRCHQ.CHQ_STS = WS_CHQ_STS;
            T000_REWRITE_DDTCHQ();
            T000_READNEXT_DDTCHQ();
        }
        CEP.TRC(SCCGWA, WS_INV_TCNT);
        CEP.TRC(SCCGWA, WS_L_TCNT);
    }
    public void R000_CHK_OUTPUT_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = DDCSIACC.AC;
        S000_CALL_CIZQACRI();
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.col = "CUS_AC,AC_STS";
        DDTMST_RD.where = "CUS_AC = :DDRMST.KEY.CUS_AC";
        IBS.READ(SCCGWA, DDRMST, this, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_STARTBR_DDTCCY() throws IOException,SQLException,Exception {
        WS_DDTCCY_FLG = 'N';
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = DDCSIACC.AC;
        DDTCCY_BR.rp = new DBParm();
        DDTCCY_BR.rp.TableName = "DDTCCY";
        DDTCCY_BR.rp.where = "CUS_AC = :DDRCCY.CUS_AC";
        DDTCCY_BR.rp.order = "CCY,CCY_TYPE";
        IBS.STARTBR(SCCGWA, DDRCCY, this, DDTCCY_BR);
    }
    public void T000_READNEXT_DDTCCY() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRCCY, this, DDTCCY_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTCCY_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_DDTCCY_FLG = 'N';
        } else {
            CEP.ERR(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        }
    }
    public void T000_ENDBR_DDTCCY() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTCCY_BR);
    }
    public void T000_STARTBR_DDTCHQ() throws IOException,SQLException,Exception {
        WS_CHQ_FLG = 'N';
        DDTCHQ_BR.rp = new DBParm();
        DDTCHQ_BR.rp.TableName = "DDTCHQ";
        DDTCHQ_BR.rp.where = "AC = :DDRCHQ.KEY.AC";
        DDTCHQ_BR.rp.order = "CHQ_TYP, STR_CHQ_NO";
        IBS.STARTBR(SCCGWA, DDRCHQ, this, DDTCHQ_BR);
    }
    public void T000_READNEXT_DDTCHQ() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRCHQ, this, DDTCHQ_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CHQ_FLG = 'Y';
        } else {
            WS_CHQ_FLG = 'N';
        }
    }
    public void T000_ENDBR_DDTCHQ() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTCHQ_BR);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void T000_READ_DDTCCY_PROC() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND);
        }
    }
    public void T000_READUP_DDTCHQ() throws IOException,SQLException,Exception {
        DDTCHQ_RD = new DBParm();
        DDTCHQ_RD.TableName = "DDTCHQ";
        DDTCHQ_RD.col = "AC,STR_CHQ_NO,END_CHQ_NO,CHQ_STS";
        DDTCHQ_RD.upd = true;
        IBS.READ(SCCGWA, DDRCHQ, DDTCHQ_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQB_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_REWRITE_DDTCHQ() throws IOException,SQLException,Exception {
        DDRCHQ.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCHQ.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTCHQ_RD = new DBParm();
        DDTCHQ_RD.TableName = "DDTCHQ";
        IBS.REWRITE(SCCGWA, DDRCHQ, DDTCHQ_RD);
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        if (CICQACRI.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACRI.RC);
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
