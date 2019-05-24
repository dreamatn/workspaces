package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.DD.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSACCK {
    int JIBS_tmp_int;
    DBParm DCTCITCD_RD;
    DBParm DCTCDDAT_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DC921";
    String WS_ERR_MSG = " ";
    String WS_CARD_NO = " ";
    String WS_AC_NO = " ";
    String WS_CI_NO = " ";
    String WS_ID_TYP = " ";
    String WS_ID_NO = " ";
    String WS_CI_NAME = " ";
    String WS_SOCIAL_NO = " ";
    char WS_CNT_FLG = ' ';
    char WS_TBL_FLAG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCRCITCD DCRCITCD = new DCRCITCD();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    CICCUST CICCUST = new CICCUST();
    DDCIMCCY DDCIMCCY = new DDCIMCCY();
    DCCF921 DCCF921 = new DCCF921();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DCCS9210 DCCHISTY = new DCCS9210();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCS9210 DCCS9210;
    public void MP(SCCGWA SCCGWA, DCCS9210 DCCS9210) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCS9210 = DCCS9210;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSACCK return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        WS_TBL_FLAG = ' ';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
        B030_HISTORY_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCS9210.SOCIAL_NO);
        CEP.TRC(SCCGWA, DCCS9210.ID_TYP);
        CEP.TRC(SCCGWA, DCCS9210.ID_NO);
        CEP.TRC(SCCGWA, DCCS9210.CI_NM);
        CEP.TRC(SCCGWA, DCCS9210.TXN_DATE);
        CEP.TRC(SCCGWA, DCCS9210.TXN_JRNNO);
        if (DCCS9210.SOCIAL_NO.trim().length() > 0) {
            IBS.init(SCCGWA, DCRCITCD);
            DCRCITCD.SOCIAL_NO = DCCS9210.SOCIAL_NO;
            WS_SOCIAL_NO = DCCS9210.SOCIAL_NO;
            T000_READ_DCTCITCD();
            if (pgmRtn) return;
            if (WS_TBL_FLAG == 'Y') {
                WS_CARD_NO = DCRCITCD.KEY.CARD_NO;
                CEP.TRC(SCCGWA, WS_CARD_NO);
                IBS.init(SCCGWA, CICCUST);
                CICCUST.FUNC = 'A';
                CICCUST.DATA.AGR_NO = WS_CARD_NO;
                S000_CALL_CIZCUST();
                if (pgmRtn) return;
                WS_ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
                WS_ID_NO = CICCUST.O_DATA.O_ID_NO;
                WS_CI_NAME = CICCUST.O_DATA.O_CI_NM;
                CEP.TRC(SCCGWA, WS_ID_TYP);
                CEP.TRC(SCCGWA, WS_ID_NO);
                CEP.TRC(SCCGWA, WS_CI_NAME);
            }
        }
        if (DCCS9210.ID_TYP.trim().length() > 0 
            && DCCS9210.ID_NO.trim().length() > 0 
            && DCCS9210.CI_NM.trim().length() > 0) {
            IBS.init(SCCGWA, CICCUST);
            CICCUST.FUNC = 'B';
            CICCUST.DATA.ID_TYPE = DCCS9210.ID_TYP;
            WS_ID_TYP = DCCS9210.ID_TYP;
            CICCUST.DATA.ID_NO = DCCS9210.ID_NO;
            WS_ID_NO = DCCS9210.ID_NO;
            CICCUST.DATA.CI_NM = DCCS9210.CI_NM;
            WS_CI_NAME = DCCS9210.CI_NM;
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_NO);
            IBS.init(SCCGWA, DCRCDDAT);
            DCRCDDAT.CARD_HLDR_CINO = CICCUST.O_DATA.O_CI_NO;
            DCRCDDAT.PROD_CD = "1203010101";
            DCRCDDAT.CARD_STS = 'N';
            T000_READ_DCTCDDAT();
            if (pgmRtn) return;
            if (WS_TBL_FLAG == 'Y') {
                WS_CARD_NO = DCRCDDAT.KEY.CARD_NO;
                CEP.TRC(SCCGWA, WS_CARD_NO);
                IBS.init(SCCGWA, DCRCITCD);
                DCRCITCD.KEY.CARD_NO = WS_CARD_NO;
                T000_READ_DCTCITCD2();
                if (pgmRtn) return;
                if (WS_TBL_FLAG == 'Y') {
                    CEP.TRC(SCCGWA, DCRCITCD.SOCIAL_NO);
                    WS_SOCIAL_NO = DCRCITCD.SOCIAL_NO;
                }
            }
        }
    }
    public void B020_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCF921);
        DCCF921.CARD_NO = WS_CARD_NO;
        DCCF921.ID_TYP = WS_ID_TYP;
        DCCF921.ID_NO = WS_ID_NO;
        DCCF921.CI_NAME = WS_CI_NAME;
        DCCF921.SOCIAL_NO = WS_SOCIAL_NO;
        DCCF921.TXN_DATE = DCCS9210.TXN_DATE;
        DCCF921.TXN_JRNNO = "" + DCCS9210.TXN_JRNNO;
        JIBS_tmp_int = DCCF921.TXN_JRNNO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) DCCF921.TXN_JRNNO = "0" + DCCF921.TXN_JRNNO;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DCCF921;
        SCCFMT.DATA_LEN = 416;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B030_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCHISTY);
        IBS.CLONE(SCCGWA, DCCS9210, DCCHISTY);
        H000_HISTORY_RECORD();
        if (pgmRtn) return;
    }
    public void H000_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.TX_RMK = "CHECK CARD AC              ";
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID = "DCCS9210";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_TOOL = WS_CARD_NO;
        BPCPNHIS.INFO.CI_NO = WS_CI_NO;
        BPCPNHIS.INFO.FMT_ID_LEN = 377;
        BPCPNHIS.INFO.NEW_DAT_PT = DCCHISTY;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void T000_READ_DCTCITCD() throws IOException,SQLException,Exception {
        DCTCITCD_RD = new DBParm();
        DCTCITCD_RD.TableName = "DCTCITCD";
        DCTCITCD_RD.where = "SOCIAL_NO = :DCRCITCD.SOCIAL_NO";
        DCTCITCD_RD.fst = true;
        DCTCITCD_RD.order = "SYNC_DT DESC";
        IBS.READ(SCCGWA, DCRCITCD, this, DCTCITCD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCITCD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTCITCD2() throws IOException,SQLException,Exception {
        DCTCITCD_RD = new DBParm();
        DCTCITCD_RD.TableName = "DCTCITCD";
        IBS.READ(SCCGWA, DCRCITCD, DCTCITCD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCITCD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.where = "CARD_HLDR_CINO = :DCRCDDAT.CARD_HLDR_CINO "
            + "AND PROD_CD = :DCRCDDAT.PROD_CD "
            + "AND CARD_STS = :DCRCDDAT.CARD_STS";
        DCTCDDAT_RD.fst = true;
        DCTCDDAT_RD.order = "ISSU_DT DESC";
        IBS.READ(SCCGWA, DCRCDDAT, this, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDDAT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CISOCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICCUST.RC);
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
