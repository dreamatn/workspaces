package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.DC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZIMFRC {
    DBParm DDTFRAC_RD;
    boolean pgmRtn = false;
    String CPN_UNI_CIZACCU = "CI-INQ-ACCU";
    String K_HIS_COPYBOOK_NAME = "DDCIMFRC";
    String K_HIS_TX_CD = "0115340";
    String K_HIS_REMARKS = "MANTAIN AC-STS-WORD";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String WS_ERR_MSG = " ";
    short WS_IDX = 0;
    String WS_VIA_AC = " ";
    String WS_AC_NO = " ";
    String WS_CARD_NO = " ";
    String WS_STD_AC = " ";
    String WS_OLD_AC = " ";
    char WS_FOUND_FLG = ' ';
    char WS_FREE_AC_FLG = ' ';
    DDZIMFRC_WS_IMFRC_INF WS_IMFRC_INF = new DDZIMFRC_WS_IMFRC_INF();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDRFRAC DDRFRAC = new DDRFRAC();
    SCCCLDT SCCCLDT = new SCCCLDT();
    DCCPACTY DCCPACTY = new DCCPACTY();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    DDCIMFRC DDCIMFRC;
    public void MP(SCCGWA SCCGWA, DDCIMFRC DDCIMFRC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCIMFRC = DDCIMFRC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZIMFRC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        WS_FOUND_FLG = 'N';
        WS_FREE_AC_FLG = 'N';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_INQ_AC_MFRC_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCIMFRC.FUNC);
        CEP.TRC(SCCGWA, DDCIMFRC.CI_NO);
        CEP.TRC(SCCGWA, DDCIMFRC.AC);
        WS_IMFRC_INF.WS_CI_NO = DDCIMFRC.CI_NO;
        WS_AC_NO = DDCIMFRC.AC;
        if (DDCIMFRC.FUNC != '1' 
            && DDCIMFRC.FUNC != '2' 
            && DDCIMFRC.FUNC != '0') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_FUNC_M_ERORR, DDCIMFRC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DDCIMFRC.AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT, DDCIMFRC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        B011_GET_STD_AC_PROC();
        if (pgmRtn) return;
        WS_STD_AC = DCCPACTY.OUTPUT.STD_AC;
    }
    public void B020_INQ_AC_MFRC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRFRAC);
        DDRFRAC.KEY.AC = WS_STD_AC;
        CEP.TRC(SCCGWA, DDRFRAC.KEY.AC);
        if (DDCIMFRC.FUNC == '0') {
            T000_READ_FRAC_ALL();
            if (pgmRtn) return;
        } else if (DDCIMFRC.FUNC == '1') {
            T000_READ_FRAC_TLR();
            if (pgmRtn) return;
        } else if (DDCIMFRC.FUNC == '2') {
            T000_READ_FRAC_CINO();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DDCIMFRC);
        if (WS_FOUND_FLG == 'F') {
            WS_FREE_AC_FLG = 'Y';
            DDCIMFRC.CI_NO = DDRFRAC.KEY.CI_NO;
            DDCIMFRC.STR_DATE = DDRFRAC.STR_DATE;
            DDCIMFRC.END_DATE = DDRFRAC.END_DATE;
            DDCIMFRC.FLG = DDRFRAC.KEY.FLG;
            DDCIMFRC.FREE_TYP = DDRFRAC.FREE_TYP;
            DDCIMFRC.FREE_TENOR = DDRFRAC.FREE_TENOR;
            DDCIMFRC.FREE_CODE = DDRFRAC.FREE_CODE;
            DDCIMFRC.FREE_REASON = DDRFRAC.FREE_REASON;
        }
        DDCIMFRC.AC = WS_AC_NO;
        DDCIMFRC.FREE_AC_FLG = WS_FREE_AC_FLG;
        CEP.TRC(SCCGWA, DDCIMFRC.FREE_AC_FLG);
        CEP.TRC(SCCGWA, DDCIMFRC.FLG);
    }
    public void B011_GET_STD_AC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCPACTY);
        DCCPACTY.INPUT.AC = WS_AC_NO;
        DCCPACTY.INPUT.FUNC = '3';
        S000_CALL_DCZPACTY();
        if (pgmRtn) return;
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_FRAC_CINO() throws IOException,SQLException,Exception {
        DDTFRAC_RD = new DBParm();
        DDTFRAC_RD.TableName = "DDTFRAC";
        DDTFRAC_RD.where = "AC = :DDRFRAC.KEY.AC "
            + "AND FLG IN ( '1' , '2' )";
        DDTFRAC_RD.fst = true;
        IBS.READ(SCCGWA, DDRFRAC, this, DDTFRAC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_FLG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FOUND_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ TABLE DDTFRAC ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTFRAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_FRAC_TLR() throws IOException,SQLException,Exception {
        DDTFRAC_RD = new DBParm();
        DDTFRAC_RD.TableName = "DDTFRAC";
        DDTFRAC_RD.where = "AC = :DDRFRAC.KEY.AC "
            + "AND FLG = '3'";
        DDTFRAC_RD.fst = true;
        IBS.READ(SCCGWA, DDRFRAC, this, DDTFRAC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_FLG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FOUND_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ TABLE DDTFRAC ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTFRAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_FRAC_ALL() throws IOException,SQLException,Exception {
        DDTFRAC_RD = new DBParm();
        DDTFRAC_RD.TableName = "DDTFRAC";
        DDTFRAC_RD.where = "AC = :DDRFRAC.KEY.AC";
        DDTFRAC_RD.fst = true;
        IBS.READ(SCCGWA, DDRFRAC, this, DDTFRAC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_FLG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FOUND_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ TABLE DDTFRAC ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTFRAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (DDCIMFRC.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DDCIMFRC=");
            CEP.TRC(SCCGWA, DDCIMFRC);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
