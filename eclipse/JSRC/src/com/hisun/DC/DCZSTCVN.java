package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSTCVN {
    int JIBS_tmp_int;
    DBParm DCTCDDAT_RD;
    DBParm DCTINRCD_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String DCZUCINF = "DC-U-CARD-INF";
    String REC_NHIS = "BP-REC-NHIS";
    String SCSSCLDT = "SCSSCLDT";
    String OUTPUT_FMT = "DC290";
    DCZSTCVN_WS_VARIABLES WS_VARIABLES = new DCZSTCVN_WS_VARIABLES();
    DCZSTCVN_WS_OUTPUT WS_OUTPUT = new DCZSTCVN_WS_OUTPUT();
    DCCMSG_ERROR_MSG ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCWRMSG SCCWRMSG = new SCCWRMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DCRINRCD DCRINRCD = new DCRINRCD();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DCCUCINF DCCUCINF = new DCCUCINF();
    DCCHTMPL DCCHTMPL = new DCCHTMPL();
    DCCHTMPL DCCHTMPL = new DCCHTMPL();
    DCCHTMPL DCCHTMPL = new DCCHTMPL();
    CICCUST CICCUST = new CICCUST();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    DCCSTCVN DCCSTCVN;
    public void MP(SCCGWA SCCGWA, DCCSTCVN DCCSTCVN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSTCVN = DCCSTCVN;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSTCVN return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_CANCEL_LOCK();
        if (pgmRtn) return;
        B040_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
        B050_HISTORY_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSTCVN.ID_TYPE);
        CEP.TRC(SCCGWA, DCCSTCVN.ID_NO);
        if (DCCSTCVN.FUNC_CODE != 'C') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_FUNC_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if ((DCCSTCVN.ID_TYPE.trim().length() > 0 
            && DCCSTCVN.ID_NO.trim().length() == 0) 
            || (DCCSTCVN.ID_TYPE.trim().length() == 0 
            && DCCSTCVN.ID_NO.trim().length() > 0)) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_IDTYPE_IDNO_SAMETIME;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_CANCEL_LOCK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUCINF);
        DCCUCINF.CARD_NO = DCCSTCVN.CARD_NO;
        S000_CALL_DCZUCINF();
        if (pgmRtn) return;
        if (DCCUCINF.CARD_STS == 'C') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_CARD_NOT_NORMAL_STS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (!DCCUCINF.CARD_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_CARD_NOT_LOCK;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSTCVN.ID_TYPE.trim().length() > 0 
            && DCCSTCVN.ID_NO.trim().length() > 0) {
            if (DCCUCINF.CARD_HLDR_CINO.trim().length() > 0) {
                IBS.init(SCCGWA, CICCUST);
                CICCUST.FUNC = 'C';
                CICCUST.DATA.CI_NO = DCCUCINF.CARD_HLDR_CINO;
                S000_CALL_CIZCUST();
                if (pgmRtn) return;
                WS_OUTPUT.ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
                WS_OUTPUT.ID_NO = CICCUST.O_DATA.O_ID_NO;
            } else {
                WS_OUTPUT.ID_TYP = DCCUCINF.CARD_HLDR_IDTYP;
                WS_OUTPUT.ID_NO = DCCUCINF.CARD_HLDR_IDNO;
            }
            if (!DCCSTCVN.ID_NO.equalsIgnoreCase(WS_OUTPUT.ID_NO)) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_IDNO_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (!DCCSTCVN.ID_TYPE.equalsIgnoreCase(WS_OUTPUT.ID_TYP)) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_IDTYP_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCSTCVN.CARD_NO;
        T000_READUPD_DCTCDDAT();
        if (pgmRtn) return;
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        DCRCDDAT.CARD_STSW = DCRCDDAT.CARD_STSW.substring(0, 8 - 1) + "0" + DCRCDDAT.CARD_STSW.substring(8 + 1 - 1);
        DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_UPDATE_DCTCDDAT();
        if (pgmRtn) return;
    }
    public void B030_INRCD_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRINRCD);
        DCRINRCD.KEY.CARD_NO = DCRCDDAT.KEY.CARD_NO;
        DCRINRCD.KEY.TR_JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        if (DCCSTCVN.FUNC_CODE == 'C') {
            DCRINRCD.KEY.INCD_TYPE = "15";
        }
        DCRINRCD.TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DCRINRCD.AUT_TLR = SCCGWA.COMM_AREA.SUP1_ID;
        DCRINRCD.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRINRCD.CRT_TM = SCCGWA.COMM_AREA.TR_TIME;
        DCRINRCD.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRINRCD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRINRCD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_DCTINRCD();
        if (pgmRtn) return;
    }
    public void B040_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT);
        WS_OUTPUT.FUNC_CODE = DCCSTCVN.FUNC_CODE;
        WS_OUTPUT.CARD_NO = DCCSTCVN.CARD_NO;
        WS_OUTPUT.ID_TYP = DCCSTCVN.ID_TYPE;
        WS_OUTPUT.ID_NO = DCCSTCVN.ID_NO;
        if (DCCUCINF.CARD_HLDR_CNM.trim().length() == 0) {
            WS_OUTPUT.CI_NM = DCCUCINF.CARD_HLDR_ENM;
        } else {
            WS_OUTPUT.CI_NM = DCCUCINF.CARD_HLDR_CNM;
        }
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("0")) {
            WS_OUTPUT.CRD_LOCK_FLG = 'N';
        } else {
            WS_OUTPUT.CRD_LOCK_FLG = 'Y';
        }
        WS_OUTPUT.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        WS_OUTPUT.LAST_USER = SCCGWA.COMM_AREA.TL_ID;
        WS_OUTPUT.LAST_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUTPUT;
        SCCFMT.DATA_LEN = 372;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B050_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCHTMPL);
        IBS.init(SCCGWA, DCCHTMPL);
        IBS.init(SCCGWA, DCCHTMPL);
        DCCHTMPL.FUNC_CODE = DCCSTCVN.FUNC_CODE;
        DCCHTMPL.CARD_NO = DCCSTCVN.CARD_NO;
        DCCHTMPL.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCCHTMPL.LAST_USER = SCCGWA.COMM_AREA.TL_ID;
        DCCHTMPL.LAST_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.TX_RMK = "CANCEL LOCK OF CVN OR OTHER";
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID = "DCCSTCVN";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_TOOL = DCCSTCVN.CARD_NO;
        BPCPNHIS.INFO.AC = DCCSTCVN.CARD_NO;
        BPCPNHIS.INFO.TX_TYP_CD = "PB03";
        BPCPNHIS.INFO.CI_NO = DCRCDDAT.CARD_OWN_CINO;
        BPCPNHIS.INFO.FMT_ID_LEN = 770;
        BPCPNHIS.INFO.OLD_DAT_PT = DCCHTMPL;
        BPCPNHIS.INFO.NEW_DAT_PT = DCCHTMPL;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, DCZUCINF, DCCUCINF);
        if (DCCUCINF.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CISOCUST", CICCUST);
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.where = "CARD_NO = :DCRCDDAT.KEY.CARD_NO";
        IBS.READ(SCCGWA, DCRCDDAT, this, DCTCDDAT_RD);
    }
    public void T000_UPDATE_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.REWRITE(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
    }
    public void T000_WRITE_DCTINRCD() throws IOException,SQLException,Exception {
        DCTINRCD_RD = new DBParm();
        DCTINRCD_RD.TableName = "DCTINRCD";
        IBS.WRITE(SCCGWA, DCRINRCD, DCTINRCD_RD);
    }
    public void T000_READUPD_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.upd = true;
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
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
