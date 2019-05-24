package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class DCZUDACH {
    String JIBS_tmp_str[] = new String[10];
    DBParm DCTDIRAC_RD;
    DBParm DCTCDDAT_RD;
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    char WS_HOLD_AC_FLG = ' ';
    char WS_TBL_FLAG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DCRDIRAC DCRDIRAC = new DCRDIRAC();
    BPCPRMR BPCPRMR = new BPCPRMR();
    DCRPRDPR DCRPRDPR = new DCRPRDPR();
    DCCUPRCD DCCUPRCD = new DCCUPRCD();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    DCCUDACH DCCUDACH;
    public void MP(SCCGWA SCCGWA, DCCUDACH DCCUDACH) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCUDACH = DCCUDACH;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZUDACH return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_CHECK_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCUDACH.DATA.FLG);
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCUDACH);
        CEP.TRC(SCCGWA, DCCUDACH.DATA.CARD_NO);
        if (DCCUDACH.DATA.CARD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO, DCCUDACH.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUDACH.DATA.BANK_FLG == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_BANK_FLG;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_BANK_FLG, DCCUDACH.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUDACH.DATA.AC_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_AC_NO;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_AC_NO, DCCUDACH.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUDACH.DATA.BANK_FLG == '1') {
            if (DCCUDACH.DATA.BR == ' ') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_BR;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_BR, DCCUDACH.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else if (DCCUDACH.DATA.BANK_FLG == '2') {
            if (DCCUDACH.DATA.OTH_BANK_NO.trim().length() == 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_OTH_BR;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_OTH_BR, DCCUDACH.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_CHECK_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCUDACH.DATA.CARD_NO;
        T000_READ_DCTCDDAT();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'N') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST, DCCUDACH.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRCDDAT.CARD_STS != 'N') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_NORMAL_STS;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NOT_NORMAL_STS, DCCUDACH.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_HOLD_AC_FLG = DCRCDDAT.HOLD_AC_FLG;
        CEP.TRC(SCCGWA, WS_HOLD_AC_FLG);
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = DCRCDDAT.PROD_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
        if (BPCPQPRD.PARM_CODE.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PQPRD_PARM_CD_SPC;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_PQPRD_PARM_CD_SPC, DCCUDACH.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DCRPRDPR);
        IBS.init(SCCGWA, DCCUPRCD);
        DCCUPRCD.TX_TYPE = 'I';
        DCCUPRCD.DATA.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        DCCUPRCD.DATA.VAL.PRDMO_CD = "CARD";
        DCCUPRCD.DATA.KEY.CD.PARM_CODE = BPCPQPRD.PARM_CODE;
        S000_CALL_DCZUPRCD();
        if (pgmRtn) return;
        if (DCCUPRCD.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DCCUPRCD.RC);
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUPRCD.DATA);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCRPRDPR);
        }
        CEP.TRC(SCCGWA, DCRPRDPR);
        if (DCRPRDPR.DATA_TXT.ADSC_TYP == 'C') {
            B030_CHECK_DIRECTIONAL_ACCOUNT();
            if (pgmRtn) return;
        }
        if (DCRPRDPR.DATA_TXT.ADSC_TYP == 'P') {
            DCCUDACH.DATA.FLG = 'Y';
        }
    }
    public void B030_CHECK_DIRECTIONAL_ACCOUNT() throws IOException,SQLException,Exception {
        if (WS_HOLD_AC_FLG == 'Y') {
            IBS.init(SCCGWA, DCRDIRAC);
            CEP.TRC(SCCGWA, DCCUDACH);
            DCRDIRAC.KEY.CARD_NO = DCCUDACH.DATA.CARD_NO;
            DCRDIRAC.KEY.BANK_FLG = DCCUDACH.DATA.BANK_FLG;
            DCRDIRAC.KEY.AC_NO = DCCUDACH.DATA.AC_NO;
            DCRDIRAC.KEY.OTH_BANK_NO = DCCUDACH.DATA.OTH_BANK_NO;
            T000_READ_DCTDIRAC_F();
            if (pgmRtn) return;
            if (WS_TBL_FLAG == 'Y') {
                DCCUDACH.DATA.FLG = 'Y';
            }
            if (WS_TBL_FLAG == 'N') {
                DCCUDACH.DATA.FLG = 'N';
            }
        } else {
            DCCUDACH.DATA.FLG = 'Y';
        }
    }
    public void T000_READ_DCTDIRAC_F() throws IOException,SQLException,Exception {
        DCTDIRAC_RD = new DBParm();
        DCTDIRAC_RD.TableName = "DCTDIRAC";
        DCTDIRAC_RD.col = "CARD_NO, BANK_FLG, AC_NO, OTH_BANK_NO, BR, CI_CNAME, CI_NO, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        DCTDIRAC_RD.where = "CARD_NO = :DCRDIRAC.KEY.CARD_NO "
            + "AND BANK_FLG = :DCRDIRAC.KEY.BANK_FLG "
            + "AND AC_NO = :DCRDIRAC.KEY.AC_NO "
            + "AND OTH_BANK_NO = :DCRDIRAC.KEY.OTH_BANK_NO";
        DCTDIRAC_RD.fst = true;
        IBS.READ(SCCGWA, DCRDIRAC, this, DCTDIRAC_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTDIRAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        BPCPRMR.DAT_PTR = DCRPRDPR;
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ        ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PROD_NOTFND;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_PROD_NOTFND, DCCUDACH.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCUDACH.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_DCZUPRCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-MPRD", DCCUPRCD);
        CEP.TRC(SCCGWA, DCCUPRCD.RC);
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCUDACH.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDDAT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (DCCUDACH.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DCCUDACH=");
            CEP.TRC(SCCGWA, DCCUDACH);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
