package com.hisun.DC;

import com.hisun.DD.*;
import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSBIND {
    int JIBS_tmp_int;
    DBParm DCTCDDAT_RD;
    brParm DCTCDDAT_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DC940";
    String K_HIS_CPB_NM = "DCZSBIND";
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    char WS_CARD_SET_FLG = ' ';
    char WS_TBL_FLAG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    CICQACRI CICQACRI = new CICQACRI();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DCCPCDCK DCCPCDCK = new DCCPCDCK();
    DCCF940 DCCF940 = new DCCF940();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DCCUCINF DCCUCINF = new DCCUCINF();
    String WS_CI_NO = " ";
    SCCGWA SCCGWA;
    DCCSBIND DCCSBIND;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, DCCSBIND DCCSBIND) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSBIND = DCCSBIND;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSBIND return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B200_AC_CHECK_PRO();
        if (pgmRtn) return;
        B300_CARD_STSW_UPDATE();
        if (pgmRtn) return;
        B350_WRITE_HIS_PROC();
        if (pgmRtn) return;
        B400_TRANS_DATA_PROC();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DCCSBIND.FUNC == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_FUNC_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSBIND.CARD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSBIND.CI_NAME.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CI_NAME;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B200_AC_CHECK_PRO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = DCCSBIND.CARD_NO;
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        if (!CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC")) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_TYP_NOT_ALLOW);
        }
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCSBIND.CARD_NO;
        T000_READ_DCTCDDAT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCRCDDAT.ACNO_TYPE);
        if (DCRCDDAT.ACNO_TYPE != '3') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_III;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DCCUCINF);
        DCCUCINF.CARD_NO = DCCSBIND.CARD_NO;
        S000_CALL_DCZUCINF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCUCINF.FACE_FLG);
        if (DCCUCINF.FACE_FLG == 'Y') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_FACE_NOT_SET;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRCDDAT.CARD_STS != 'N') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CARD_NOT_NORMAL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if ((DCCSBIND.FUNC == '1' 
            && DCRCDDAT.CARD_STSW.substring(18 - 1, 18 + 1 - 1).equalsIgnoreCase("1")) 
            || (DCCSBIND.FUNC == '2' 
            && DCRCDDAT.CARD_STSW.substring(18 - 1, 18 + 1 - 1).equalsIgnoreCase("0"))) {
            if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
            JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
            CEP.TRC(SCCGWA, DCRCDDAT.CARD_STSW.substring(18 - 1, 18 + 1 - 1));
            CEP.TRC(SCCGWA, DCCSBIND.FUNC);
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_STSW_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSBIND.PSWD.trim().length() > 0) {
            IBS.init(SCCGWA, DCCPCDCK);
            DCCPCDCK.FUNC_CODE = 'P';
            DCCPCDCK.CARD_NO = DCCSBIND.CARD_NO;
            DCCPCDCK.CARD_PSW = DCCSBIND.PSWD;
            S000_CALL_DCZPCDCK();
            if (pgmRtn) return;
        }
        if (DCCSBIND.FUNC == '1') {
            WS_CI_NO = CICQACRI.O_DATA.O_CI_NO;
            CEP.TRC(SCCGWA, WS_CI_NO);
            T000_STARTBR_DCTCDDAT();
            if (pgmRtn) return;
            T000_READNEXT_DCTCDDAT();
            if (pgmRtn) return;
            while (WS_TBL_FLAG != 'N' 
                && WS_CARD_SET_FLG != '1') {
                R000_DCTCDDAT_PROCESS();
                if (pgmRtn) return;
                T000_READNEXT_DCTCDDAT();
                if (pgmRtn) return;
            }
            T000_ENDBR_DCTCDDAT();
            if (pgmRtn) return;
            if (WS_CARD_SET_FLG == '1') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_SAME_TYP_CARD_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B300_CARD_STSW_UPDATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCSBIND.CARD_NO;
        T000_READ_UPDATE_DCTCDDAT();
        if (pgmRtn) return;
        if (DCCSBIND.FUNC == '1') {
            if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
            JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
            DCRCDDAT.CARD_STSW = DCRCDDAT.CARD_STSW.substring(0, 18 - 1) + "1" + DCRCDDAT.CARD_STSW.substring(18 + 1 - 1);
        } else {
            if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
            JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
            DCRCDDAT.CARD_STSW = DCRCDDAT.CARD_STSW.substring(0, 18 - 1) + "0" + DCRCDDAT.CARD_STSW.substring(18 + 1 - 1);
        }
        DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DCTCDDAT();
        if (pgmRtn) return;
    }
    public void B350_WRITE_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.REF_NO = DCCSBIND.CARD_NO;
        BPCPNHIS.INFO.FMT_ID = K_HIS_CPB_NM;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        CEP.TRC(SCCGWA, DCCSBIND.FUNC);
        if (DCCSBIND.FUNC == '1') {
            BPCPNHIS.INFO.TX_TYP = 'A';
        } else if (DCCSBIND.FUNC == '2') {
            BPCPNHIS.INFO.TX_TYP = 'D';
        } else {
        }
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B400_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCF940);
        DCCF940.FUNC = DCCSBIND.FUNC;
        DCCF940.CARD_NO = DCCSBIND.CARD_NO;
        DCCF940.CI_NAME = DCCSBIND.CI_NAME;
        DCCF940.PSWD = DCCSBIND.PSWD;
        CEP.TRC(SCCGWA, DCCF940);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DCCF940;
        SCCFMT.DATA_LEN = 304;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        if (CICQACRI.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACRI.RC);
        }
    }
    public void S000_CALL_DCZPCDCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-P-CARD-TRKDAT-CHK", DCCPCDCK);
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF", DCCUCINF);
        if (DCCUCINF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.upd = true;
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
    }
    public void T000_REWRITE_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.REWRITE(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
    }
    public void T000_STARTBR_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_BR.rp = new DBParm();
        DCTCDDAT_BR.rp.TableName = "DCTCDDAT";
        DCTCDDAT_BR.rp.where = "CARD_HLDR_CINO = :WS_CI_NO "
            + "AND CARD_STS < > 'C'";
        IBS.STARTBR(SCCGWA, DCRCDDAT, this, DCTCDDAT_BR);
    }
    public void T000_READNEXT_DCTCDDAT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRCDDAT, this, DCTCDDAT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            WS_TBL_FLAG = 'Y';
        }
    }
    public void T000_ENDBR_DCTCDDAT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTCDDAT_BR);
    }
    public void R000_DCTCDDAT_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCRCDDAT.KEY.CARD_NO);
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        CEP.TRC(SCCGWA, DCRCDDAT.CARD_STSW.substring(18 - 1, 18 + 1 - 1));
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(18 - 1, 18 + 1 - 1).equalsIgnoreCase("1") 
            && !DCRCDDAT.KEY.CARD_NO.equalsIgnoreCase(DCCSBIND.CARD_NO)) {
            WS_CARD_SET_FLG = '1';
        }
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
