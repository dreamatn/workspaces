package com.hisun.DD;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZIMSST {
    int JIBS_tmp_int;
    DBParm DDTMST_RD;
    boolean pgmRtn = false;
    int WS_HLD_NO = 0;
    short WS_STS_NO = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDRVCH DDRVCH = new DDRVCH();
    DDRMST DDRMST = new DDRMST();
    DDRMST DDRMSTO = new DDRMST();
    DDRINF DDRINF = new DDRINF();
    SCCGWA SCCGWA;
    DDCIMSST DDCIMSST;
    public void MP(SCCGWA SCCGWA, DDCIMSST DDCIMSST) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCIMSST = DDCIMSST;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZIMSST return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        DDCIMSST.RC.RC_MMO = SCCGWA.COMM_AREA.AP_MMO;
        DDCIMSST.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (DDCIMSST.TX_TYPE == 'S') {
            B050_SET_AC_STS_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID TX TYPE(" + DDCIMSST.TX_TYPE + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B050_SET_AC_STS_PROC() throws IOException,SQLException,Exception {
        R000_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        R000_R_UPD_MST_PROC();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, DDRMST, DDRMSTO);
        R000_CHECK_MST_STUS();
        if (pgmRtn) return;
        if (DDCIMSST.DATA.STS_CD.trim().length() == 0) WS_STS_NO = 0;
        else WS_STS_NO = Short.parseShort(DDCIMSST.DATA.STS_CD);
        CEP.TRC(SCCGWA, DDCIMSST.DATA.SET_FLG);
        if (DDCIMSST.DATA.SET_FLG == 'O') {
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            DDRMST.AC_STS_WORD = DDRMST.AC_STS_WORD.substring(0, WS_STS_NO - 1) + "1" + DDRMST.AC_STS_WORD.substring(WS_STS_NO + 1 - 1);
        } else {
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            DDRMST.AC_STS_WORD = DDRMST.AC_STS_WORD.substring(0, WS_STS_NO - 1) + "0" + DDRMST.AC_STS_WORD.substring(WS_STS_NO + 1 - 1);
        }
        DDRMST.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_REWRITE_DDTMST();
        if (pgmRtn) return;
        R000_TRANS_DATA_OUT();
        if (pgmRtn) return;
    }
    public void R000_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DDCIMSST.DATA.KEY.AC_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT, DDCIMSST.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DDCIMSST.TX_TYPE == 'S') {
            if (DDCIMSST.DATA.STS_CD.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_STS_NO_M_INPUT, DDCIMSST.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if ((DDCIMSST.DATA.STS_CD.compareTo("00") < 0) 
                || (DDCIMSST.DATA.STS_CD.compareTo("99") > 0)) {
                IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_STS_NO_INVALID, DDCIMSST.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if ((DDCIMSST.DATA.SET_FLG != 'O') 
                && (DDCIMSST.DATA.SET_FLG != 'F')) {
                IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_SET_FLG_INVALID, DDCIMSST.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_R_UPD_MST_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCIMSST.DATA.KEY.AC_NO;
        T000_READUPD_DDTMST();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA_OUT() throws IOException,SQLException,Exception {
        DDCIMSST.DATA.LAST_FN_DATE = DDRMSTO.LAST_FN_DATE;
        DDCIMSST.DATA.LAST_DATE = DDRMSTO.LAST_DATE;
        DDCIMSST.DATA.LAST_TELR = DDRMSTO.LAST_TLR;
        DDCIMSST.DATA.AC_STS = DDRMSTO.AC_STS;
        CEP.TRC(SCCGWA, DDRMSTO.AC_STS_WORD);
        DDCIMSST.DATA.AC_STS_WORD = DDRMSTO.AC_STS_WORD;
        DDCIMSST.DATA.PROD_CODE = DDRMSTO.PROD_CODE;
        DDCIMSST.DATA.CCY_FLG = DDRMSTO.CCY_FLG;
        DDCIMSST.DATA.PAY_TYPE = DDRMSTO.PAY_MTH;
        DDCIMSST.DATA.OPEN_DATE = DDRMSTO.OPEN_DATE;
        DDCIMSST.DATA.EFF_DATE = DDRMSTO.EFF_DATE;
        DDCIMSST.DATA.EXP_DATE = DDRMSTO.EXP_DATE;
        DDCIMSST.DATA.PRDAC_CD = DDRMSTO.PRDAC_CD;
        DDCIMSST.DATA.AC_TYPE = DDRMSTO.AC_TYPE;
        DDCIMSST.DATA.OPEN_BK = DDRMSTO.OPEN_BK;
        DDCIMSST.DATA.OPEN_DP = DDRMSTO.OPEN_DP;
        DDCIMSST.DATA.OPEN_TLR = DDRMSTO.OPEN_TLR;
        DDCIMSST.DATA.CLOSE_DATE = DDRMSTO.CLOSE_DATE;
        DDCIMSST.DATA.CARD_FLG = DDRMSTO.CARD_FLG;
        DDCIMSST.DATA.FRG_TYPE = DDRMSTO.FRG_TYPE;
        DDCIMSST.DATA.FRG_OPEN_NO = DDRMSTO.FRG_OPEN_NO;
        DDCIMSST.DATA.CHCK_IND = DDRMSTO.CHCK_IND;
        DDCIMSST.DATA.FRG_IND = DDRMSTO.FRG_IND.charAt(0);
        DDCIMSST.DATA.AC_PURP = DDRMSTO.AC_PURP;
        DDCIMSST.DATA.FEE_METH = DDRMSTO.FEE_METH;
        DDCIMSST.DATA.CROS_DR_FLG = DDRMSTO.CROS_DR_FLG;
        DDCIMSST.DATA.CROS_CR_FLG = DDRMSTO.CROS_CR_FLG;
        DDCIMSST.DATA.SPC_KIND = DDRMSTO.SPC_KIND.charAt(0);
        DDCIMSST.DATA.CI_TYP = DDRMSTO.CI_TYP;
        DDCIMSST.DATA.PBC_APPR_DATE = DDRMSTO.PBC_APPR_DATE;
        DDCIMSST.DATA.CASH_FLG = DDRMSTO.CASH_FLG;
        DDCIMSST.DATA.AMT_TYPE = DDRINF.AMT_TYPE;
        DDCIMSST.DATA.TXN_TYPE = DDRINF.TXN_TYPE;
    }
    public void R000_CHECK_MST_STUS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRMST.AC_STS);
        if (DDRMST.AC_STS == 'C') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_CLOSE, DDCIMSST.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READUPD_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.upd = true;
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND, DDCIMSST.RC);
            DDCIMSST.RC.RC_INFO = DDCIMSST.DATA.KEY.AC_NO;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DDTMST() throws IOException,SQLException,Exception {
        DDRMST.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMST.LAST_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.REWRITE(SCCGWA, DDRMST, DDTMST_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (DDCIMSST.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DDCIMSST=");
            CEP.TRC(SCCGWA, DDCIMSST);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
