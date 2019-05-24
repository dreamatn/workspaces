package com.hisun.DD;

import com.hisun.DC.*;
import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.DB.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZUOBAL {
    int JIBS_tmp_int;
    DBParm DDTADIF_RD;
    brParm DDTADIF_BR = new brParm();
    DBParm DDTADMN_RD;
    DBParm DDTCCY_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DD631";
    String K_OUTPUT_FMT_INQ = "DD630";
    int K_SCR_ROW_NO = 8;
    String WS_ERR_MSG = " ";
    String WS_ADP_NO = " ";
    String WS_AC_CNM = " ";
    String WS_ADP_TYPE = " ";
    String WS_MSGID = " ";
    short WS_FLD_NO = 0;
    double WS_OD_BAL = 0;
    char WS_ADIF_FLG = ' ';
    char WS_ADMN_FLG = ' ';
    char WS_CHG_FLG = ' ';
    char WS_UPDATE_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DDCOADMN DDCOADMN = new DDCOADMN();
    CICACCU CICACCU = new CICACCU();
    CICQACAC CICQACAC = new CICQACAC();
    DDRCCY DDRCCY = new DDRCCY();
    DDRMST DDRMST = new DDRMST();
    DDRADMN DDRADMN = new DDRADMN();
    DDRADIF DDRADIF = new DDRADIF();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    DDCUOBAL DDCUOBAL;
    public void MP(SCCGWA SCCGWA, DDCUOBAL DDCUOBAL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCUOBAL = DDCUOBAL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZUOBAL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B200_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCUOBAL.AC);
        CEP.TRC(SCCGWA, DDCUOBAL.OD_AMT);
        if (DDCUOBAL.AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT, DDCUOBAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DDCUOBAL.OD_AMT < 0) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_OD_AMT_ERROR, DDCUOBAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B200_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRADIF);
        DDRADIF.LN_AC = DDCUOBAL.AC;
        CEP.TRC(SCCGWA, DDRADIF.LN_AC);
        T000_READUPDATE_DDTADIF();
        if (pgmRtn) return;
        if (WS_ADIF_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_ADIF_REC_NOTFND, DDCUOBAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDRADIF.OD_STS);
        if (DDRADIF.OD_STS != '4') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_OD_STS_NOTNORMAL, DDCUOBAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDRADIF.OD_BAL);
        CEP.TRC(SCCGWA, DDCUOBAL.OD_AMT);
        WS_OD_BAL = DDRADIF.OD_BAL - DDCUOBAL.OD_AMT;
        CEP.TRC(SCCGWA, WS_OD_BAL);
        if (WS_OD_BAL < 0) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_OD_AMT_ERROR, DDCUOBAL.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            DDRADIF.OD_BAL = WS_OD_BAL;
        }
        CEP.TRC(SCCGWA, DDRADIF.OD_BAL);
        if (WS_OD_BAL == 0) {
            DDRADIF.OD_STS = '2';
            DDRADIF.OD_EXPDATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        T000_REWRITE_DDTADIF();
        if (pgmRtn) return;
        if (WS_OD_BAL == 0) {
            R000_UPDATE_ADP_STSW();
            if (pgmRtn) return;
        }
    }
    public void R000_UPDATE_ADP_STSW() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRADIF.KEY.ACO_AC);
        WS_UPDATE_FLG = 'N';
        T000_STARTBR_DDTADIF();
        if (pgmRtn) return;
        T000_READNEXT_DDTADIF();
        if (pgmRtn) return;
        while (WS_ADIF_FLG != 'N' 
            && WS_UPDATE_FLG != 'Y') {
            CEP.TRC(SCCGWA, DDRADIF.OD_STS);
            if (DDRADIF.OD_STS == '3' 
                || DDRADIF.OD_STS == '4') {
                WS_UPDATE_FLG = 'Y';
            }
            T000_READNEXT_DDTADIF();
            if (pgmRtn) return;
        }
        T000_ENDBR_DDTADIF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_UPDATE_FLG);
        if (WS_UPDATE_FLG != 'Y') {
            DDRADMN.KEY.AC = DDRADIF.KEY.ACO_AC;
            T000_READUPDATE_DDTADMN();
            if (pgmRtn) return;
            if (WS_ADMN_FLG == 'F') {
                if (DDRADMN.ADP_STSW == null) DDRADMN.ADP_STSW = "";
                JIBS_tmp_int = DDRADMN.ADP_STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRADMN.ADP_STSW += " ";
                CEP.TRC(SCCGWA, DDRADMN.ADP_STSW.substring(0, 2));
                if (DDRADMN.ADP_STSW == null) DDRADMN.ADP_STSW = "";
                JIBS_tmp_int = DDRADMN.ADP_STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRADMN.ADP_STSW += " ";
                DDRADMN.ADP_STSW = "00" + DDRADMN.ADP_STSW.substring(2);
                T000_REWRITE_DDTADMN();
                if (pgmRtn) return;
            }
        }
    }
    public void T000_READUPDATE_DDTADIF() throws IOException,SQLException,Exception {
        DDTADIF_RD = new DBParm();
        DDTADIF_RD.TableName = "DDTADIF";
        DDTADIF_RD.where = "LN_AC = :DDRADIF.LN_AC";
        DDTADIF_RD.upd = true;
        DDTADIF_RD.fst = true;
        IBS.READ(SCCGWA, DDRADIF, this, DDTADIF_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ADIF_FLG = 'F';
        } else {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                WS_ADIF_FLG = 'N';
            }
        }
    }
    public void T000_REWRITE_DDTADIF() throws IOException,SQLException,Exception {
        DDRADIF.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRADIF.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTADIF_RD = new DBParm();
        DDTADIF_RD.TableName = "DDTADIF";
        IBS.REWRITE(SCCGWA, DDRADIF, DDTADIF_RD);
    }
    public void T000_STARTBR_DDTADIF() throws IOException,SQLException,Exception {
        DDTADIF_BR.rp = new DBParm();
        DDTADIF_BR.rp.TableName = "DDTADIF";
        DDTADIF_BR.rp.where = "ACO_AC = :DDRADIF.KEY.ACO_AC";
        IBS.STARTBR(SCCGWA, DDRADIF, this, DDTADIF_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READNEXT_DDTADIF() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRADIF, this, DDTADIF_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ADIF_FLG = 'F';
        } else {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                WS_ADIF_FLG = 'N';
            }
        }
    }
    public void T000_ENDBR_DDTADIF() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTADIF_BR);
    }
    public void T000_READUPDATE_DDTADMN() throws IOException,SQLException,Exception {
        DDTADMN_RD = new DBParm();
        DDTADMN_RD.TableName = "DDTADMN";
        DDTADMN_RD.where = "AC = :DDRADMN.KEY.AC "
            + "AND ADP_STS = 'O'";
        DDTADMN_RD.upd = true;
        IBS.READ(SCCGWA, DDRADMN, this, DDTADMN_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ADMN_FLG = 'F';
        } else {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                WS_ADMN_FLG = 'N';
            }
        }
    }
    public void T000_REWRITE_DDTADMN() throws IOException,SQLException,Exception {
        DDRADMN.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRADMN.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTADMN_RD = new DBParm();
        DDTADMN_RD.TableName = "DDTADMN";
        IBS.REWRITE(SCCGWA, DDRADMN, DDTADMN_RD);
    }
    public void R000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
    }
    public void T000_READ_DDTADIF() throws IOException,SQLException,Exception {
        DDTADIF_RD = new DBParm();
        DDTADIF_RD.TableName = "DDTADIF";
        IBS.READ(SCCGWA, DDRADIF, DDTADIF_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ADIF_FLG = 'F';
        } else {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                WS_ADIF_FLG = 'N';
            }
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
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
        CEP.TRC(SCCGWA, DDCUOBAL.RC);
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
