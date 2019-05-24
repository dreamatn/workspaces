package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZIMMST {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm DDTVCH_RD;
    DBParm DDTMST_RD;
    DBParm DDTINF_RD;
    DBParm DDTCCY_RD;
    boolean pgmRtn = false;
    int WS_HLD_NO = 0;
    short WS_STS_NO = 0;
    char WS_BV_TYPE = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDRVCH DDRVCH = new DDRVCH();
    DDRMST DDRMST = new DDRMST();
    DDRMST DDRMSTO = new DDRMST();
    DDRINF DDRINF = new DDRINF();
    DDRCCY DDRCCY = new DDRCCY();
    CICQACAC CICQACAC = new CICQACAC();
    CICQACRI CICQACRI = new CICQACRI();
    SCCGWA SCCGWA;
    DDCIMMST DDCIMMST;
    public void MP(SCCGWA SCCGWA, DDCIMMST DDCIMMST) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCIMMST = DDCIMMST;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZIMMST return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        DDCIMMST.RC.RC_MMO = SCCGWA.COMM_AREA.AP_MMO;
        DDCIMMST.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (DDCIMMST.TX_TYPE == 'I') {
            B010_INQ_AC_INF_PROC();
            if (pgmRtn) return;
        } else if (DDCIMMST.TX_TYPE == 'U') {
            B030_UPD_AC_INF_PROC();
            if (pgmRtn) return;
        } else if (DDCIMMST.TX_TYPE == 'S') {
            B050_SET_AC_STS_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID TX TYPE(" + DDCIMMST.TX_TYPE + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_INQ_AC_INF_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCIMMST.DATA.KEY.AC_NO);
        R000_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCIMMST.DATA.KEY.AC_NO;
        T000_READ_DDTMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRMST.AC_STS);
        if (DDRMST.CI_TYP == '2' 
            || DDRMST.CI_TYP == '3') {
            IBS.init(SCCGWA, DDRINF);
            DDRINF.KEY.CUS_AC = DDCIMMST.DATA.KEY.AC_NO;
            T000_READ_DDTINF();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, DDRMST, DDRMSTO);
        IBS.init(SCCGWA, DDRVCH);
        DDRVCH.KEY.CUS_AC = DDRMST.KEY.CUS_AC;
        CEP.TRC(SCCGWA, DDRMST.KEY.CUS_AC);
        T000_READ_DDTVCH();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRMST.KEY.CUS_AC);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, DDRVCH.KEY.BV_TYPE);
        DDRMSTO.PAY_MTH = DDRVCH.PAY_TYPE;
        WS_BV_TYPE = DDRVCH.KEY.BV_TYPE;
        CEP.TRC(SCCGWA, DDRMSTO.PAY_MTH);
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.DATA.AGR_NO = DDCIMMST.DATA.KEY.AC_NO;
        CICQACRI.FUNC = 'A';
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_CCY);
        R000_TRANS_DATA_OUT();
        if (pgmRtn) return;
    }
    public void B030_UPD_AC_INF_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCIMMST.DATA.KEY.AC_NO);
        R000_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        R000_R_UPD_MST_PROC();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, DDRMST, DDRMSTO);
        R000_CHECK_MST_STUS();
        if (pgmRtn) return;
        DDRMST.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMST.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_REWRITE_DDTMST();
        if (pgmRtn) return;
        R000_TRANS_DATA_OUT();
        if (pgmRtn) return;
    }
    public void B050_SET_AC_STS_PROC() throws IOException,SQLException,Exception {
        R000_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        R000_R_UPD_MST_PROC();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, DDRMST, DDRMSTO);
        R000_CHECK_MST_STUS();
        if (pgmRtn) return;
        if (DDCIMMST.DATA.STS_CD.trim().length() == 0) WS_STS_NO = 0;
        else WS_STS_NO = Short.parseShort(DDCIMMST.DATA.STS_CD);
        CEP.TRC(SCCGWA, DDCIMMST.DATA.SET_FLG);
        if (DDCIMMST.DATA.SET_FLG == 'O') {
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
        if (DDCIMMST.DATA.STS_CD.equalsIgnoreCase("61")) {
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'C';
            CICQACAC.DATA.AGR_NO = DDRMST.KEY.CUS_AC;
            if (DDRMST.CCY_FLG == 'S') {
                IBS.init(SCCGWA, DDRCCY);
                DDRCCY.CUS_AC = DDRMST.KEY.CUS_AC;
                T000_READCUS_DDTCCY();
                if (pgmRtn) return;
                CICQACAC.DATA.CCY_ACAC = DDRCCY.CCY;
                CICQACAC.DATA.CR_FLG = DDRCCY.CCY_TYPE;
            } else {
                CICQACAC.DATA.CCY_ACAC = "156";
                CICQACAC.DATA.CR_FLG = '1';
            }
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            IBS.init(SCCGWA, DDRCCY);
            DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            T000_READUPD_DDTCCY();
            if (pgmRtn) return;
            if (DDCIMMST.DATA.SET_FLG == 'O') {
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 61 - 1) + "1" + DDRCCY.STS_WORD.substring(61 + 1 - 1);
            } else {
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 61 - 1) + "0" + DDRCCY.STS_WORD.substring(61 + 1 - 1);
            }
            T000_REWRITE_DDTCCY();
            if (pgmRtn) return;
        }
        R000_TRANS_DATA_OUT();
        if (pgmRtn) return;
    }
    public void R000_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DDCIMMST.DATA.KEY.AC_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT, DDCIMMST.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DDCIMMST.TX_TYPE == 'S') {
            if (DDCIMMST.DATA.STS_CD.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_STS_NO_M_INPUT, DDCIMMST.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if ((DDCIMMST.DATA.STS_CD.compareTo("00") < 0) 
                || (DDCIMMST.DATA.STS_CD.compareTo("99") > 0)) {
                IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_STS_NO_INVALID, DDCIMMST.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if ((DDCIMMST.DATA.SET_FLG != 'O') 
                && (DDCIMMST.DATA.SET_FLG != 'F')) {
                IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_SET_FLG_INVALID, DDCIMMST.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_R_UPD_MST_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCIMMST.DATA.KEY.AC_NO;
        T000_READUPD_DDTMST();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA_OUT() throws IOException,SQLException,Exception {
        DDCIMMST.DATA.LAST_FN_DATE = DDRMSTO.LAST_FN_DATE;
        DDCIMMST.DATA.LAST_DATE = DDRMSTO.LAST_DATE;
        DDCIMMST.DATA.LAST_TELR = DDRMSTO.LAST_TLR;
        DDCIMMST.DATA.AC_STS = DDRMSTO.AC_STS;
        CEP.TRC(SCCGWA, DDRMSTO.AC_STS_WORD);
        DDCIMMST.DATA.AC_STS_WORD = DDRMSTO.AC_STS_WORD;
        DDCIMMST.DATA.PROD_CODE = DDRMSTO.PROD_CODE;
        DDCIMMST.DATA.CCY_FLG = DDRMSTO.CCY_FLG;
        DDCIMMST.DATA.OWNER_BR = DDRMSTO.OWNER_BR;
        DDCIMMST.DATA.PAY_TYPE = DDRMSTO.PAY_MTH;
        DDCIMMST.DATA.OPEN_DATE = DDRMSTO.OPEN_DATE;
        DDCIMMST.DATA.EFF_DATE = DDRMSTO.EFF_DATE;
        DDCIMMST.DATA.EXP_DATE = DDRMSTO.EXP_DATE;
        DDCIMMST.DATA.PRDAC_CD = DDRMSTO.PRDAC_CD;
        DDCIMMST.DATA.AC_TYPE = DDRMSTO.AC_TYPE;
        DDCIMMST.DATA.OPEN_BK = DDRMSTO.OPEN_BK;
        DDCIMMST.DATA.OPEN_DP = DDRMSTO.OPEN_DP;
        DDCIMMST.DATA.OPEN_TLR = DDRMSTO.OPEN_TLR;
        DDCIMMST.DATA.CLOSE_DATE = DDRMSTO.CLOSE_DATE;
        DDCIMMST.DATA.CARD_FLG = DDRMSTO.CARD_FLG;
        DDCIMMST.DATA.FRG_TYPE = DDRMSTO.FRG_TYPE;
        DDCIMMST.DATA.FRG_OPEN_NO = DDRMSTO.FRG_OPEN_NO;
        DDCIMMST.DATA.CHCK_IND = DDRMSTO.CHCK_IND;
        DDCIMMST.DATA.FRG_IND = DDRMSTO.FRG_IND;
        DDCIMMST.DATA.AC_PURP = DDRMSTO.AC_PURP;
        DDCIMMST.DATA.FEE_METH = DDRMSTO.FEE_METH;
        DDCIMMST.DATA.CROS_DR_FLG = DDRMSTO.CROS_DR_FLG;
        DDCIMMST.DATA.CROS_CR_FLG = DDRMSTO.CROS_CR_FLG;
        DDCIMMST.DATA.SPC_KIND = DDRMSTO.SPC_KIND;
        DDCIMMST.DATA.CI_TYP = DDRMSTO.CI_TYP;
        DDCIMMST.DATA.PBC_APPR_DATE = DDRMSTO.PBC_APPR_DATE;
        DDCIMMST.DATA.CASH_FLG = DDRMSTO.CASH_FLG;
        DDCIMMST.DATA.AMT_TYPE = DDRINF.AMT_TYPE;
        DDCIMMST.DATA.TXN_TYPE = DDRINF.TXN_TYPE;
        DDCIMMST.DATA.PSBK_NO = DDRVCH.PSBK_NO;
        DDCIMMST.DATA.FRG_CODE = DDRMSTO.FRG_CODE;
        DDCIMMST.DATA.YW_TYP = DDRMSTO.YW_TYP;
        DDCIMMST.DATA.BV_TYPE = WS_BV_TYPE;
    }
    public void R000_CHECK_MST_STUS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRMST.AC_STS);
        if (DDRMST.AC_STS == 'C') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_CLOSE, DDCIMMST.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDCIMMST.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTVCH() throws IOException,SQLException,Exception {
        DDTVCH_RD = new DBParm();
        DDTVCH_RD.TableName = "DDTVCH";
        DDTVCH_RD.where = "CUS_AC = :DDRVCH.KEY.CUS_AC";
        IBS.READ(SCCGWA, DDRVCH, this, DDTVCH_RD);
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND, DDCIMMST.RC);
            DDCIMMST.RC.RC_INFO = DDCIMMST.DATA.KEY.AC_NO;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTINF() throws IOException,SQLException,Exception {
        DDTINF_RD = new DBParm();
        DDTINF_RD.TableName = "DDTINF";
        IBS.READ(SCCGWA, DDRINF, DDTINF_RD);
    }
    public void T000_READUPD_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.upd = true;
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND, DDCIMMST.RC);
            DDCIMMST.RC.RC_INFO = DDCIMMST.DATA.KEY.AC_NO;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READCUS_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.where = "CUS_AC = :DDRCCY.CUS_AC";
        IBS.READ(SCCGWA, DDRCCY, this, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND, DDCIMMST.RC);
            DDCIMMST.RC.RC_INFO = DDRCCY.KEY.AC;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READUPD_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.upd = true;
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND, DDCIMMST.RC);
            DDCIMMST.RC.RC_INFO = DDRCCY.KEY.AC;
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
    public void T000_REWRITE_DDTCCY() throws IOException,SQLException,Exception {
        DDRCCY.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.REWRITE(SCCGWA, DDRCCY, DDTCCY_RD);
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        CEP.TRC(SCCGWA, CICQACRI.RC.RC_CODE);
        if (CICQACRI.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACRI.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDCIMMST.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (DDCIMMST.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DDCIMMST=");
            CEP.TRC(SCCGWA, DDCIMMST);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
