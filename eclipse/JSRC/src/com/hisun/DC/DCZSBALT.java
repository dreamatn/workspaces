package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.BP.*;
import com.hisun.DD.*;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSBALT {
    int JIBS_tmp_int;
    DBParm DCTCDDAT_RD;
    DBParm DCTINRCD_RD;
    DBParm DCTDCICT_RD;
    boolean pgmRtn = false;
    String OUTPUT_FMT = "DC333";
    DCZSBALT_WS_VARIABLES WS_VARIABLES = new DCZSBALT_WS_VARIABLES();
    DCZSBALT_WS_OUTPUT WS_OUTPUT = new DCZSBALT_WS_OUTPUT();
    char WS_CARD_FLG = ' ';
    DCZSBALT_WS_COND_FLG WS_COND_FLG = new DCZSBALT_WS_COND_FLG();
    DCCMSG_ERROR_MSG ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DCRDCICT DCRDCICT = new DCRDCICT();
    DCRINRCD DCRINRCD = new DCRINRCD();
    SCCCLDT SCCCLDT = new SCCCLDT();
    CICCUST CICCUST = new CICCUST();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DCCGTSCD DCCGTSCD = new DCCGTSCD();
    CICQCHDC CICQCHDC = new CICQCHDC();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    CICQACAC CICQACAC = new CICQACAC();
    DDCMTTP DDCMTTP = new DDCMTTP();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    DCCSBALT DCCSBALT;
    public void MP(SCCGWA SCCGWA, DCCSBALT DCCSBALT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSBALT = DCCSBALT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSBALT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
        WS_CARD_FLG = ' ';
        IBS.init(SCCGWA, WS_COND_FLG);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B030_READ_DCTINRCD_INFO();
        if (pgmRtn) return;
        B040_BALANCE_AMT_INFO();
        if (pgmRtn) return;
        B050_ADD_DCTDCICT_INFO();
        if (pgmRtn) return;
        B060_OUTPUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSBALT.CARD_NO);
        if (DCCSBALT.CARD_NO.trim().length() == 0) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_MUST_INPUT_CARD_NO;
            CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
        }
        DCRCDDAT.KEY.CARD_NO = DCCSBALT.CARD_NO;
        T000_READ_DCTCDDAT();
        if (pgmRtn) return;
        if (DCRCDDAT.CARD_STS != 'N') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_CARD_NOT_NORMAL_STS;
            CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
        }
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        DCRCDDAT.CARD_STSW = DCRCDDAT.CARD_STSW.substring(0, 15 - 1) + "1" + DCRCDDAT.CARD_STSW.substring(15 + 1 - 1);
        DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_UPDATE_DCTCDDAT();
        if (pgmRtn) return;
    }
    public void B030_READ_DCTINRCD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRINRCD);
        DCRINRCD.KEY.CARD_NO = DCCSBALT.CARD_NO;
        DCRINRCD.KEY.CARD_SEQ = 1;
        T000_READ_DCTINRCD();
        if (pgmRtn) return;
        if (DCRINRCD.KEY.INCD_TYPE.equalsIgnoreCase("11")) {
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
            CEP.TRC(SCCGWA, DCRINRCD.PEND_DT);
            if (DCRINRCD.PEND_DT > SCCGWA.COMM_AREA.AC_DATE) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_CARD_PEND_DT_ISSUE;
                CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
            }
        }
        if (DCRINRCD.PEND_STS != '2') {
            CEP.ERR(SCCGWA, ERROR_MSG.DC_PEND_STS_ERROR);
        }
    }
    public void B040_BALANCE_AMT_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIQBAL);
        DDCIQBAL.DATA.AC = DCCSBALT.CARD_NO;
        DDCIQBAL.DATA.AID = "003";
        S000_CALL_DDZIQBAL();
        if (pgmRtn) return;
        WS_VARIABLES.AMT = DDCIQBAL.DATA.NINT_BAL;
        CEP.TRC(SCCGWA, WS_VARIABLES.AMT);
        if (WS_VARIABLES.AMT > 0) {
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'I';
            CICQACAC.DATA.AGR_NO = DCCSBALT.CARD_NO;
            CICQACAC.DATA.AID = "003";
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO);
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
            IBS.init(SCCGWA, DDCMTTP);
            DDCMTTP.FUNC = '1';
            DDCMTTP.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            DDCMTTP.TAMT = WS_VARIABLES.AMT;
            S000_CALL_DDZMTTP();
            if (pgmRtn) return;
            IBS.init(SCCGWA, DDCUDRAC);
            DDCUDRAC.AC = DCCSBALT.CARD_NO;
            DDCUDRAC.CCY = "156";
            DDCUDRAC.TX_AMT = WS_VARIABLES.AMT;
            DDCUDRAC.AID = "003";
            DDCUDRAC.TX_TYPE = 'T';
            DDCUDRAC.OTHER_AC = DCCSBALT.CARD_NO;
            DDCUDRAC.OTHER_CCY = "156";
            DDCUDRAC.OTHER_AMT = WS_VARIABLES.AMT;
            DDCUDRAC.CHK_PSW_FLG = 'N';
            DDCUDRAC.SMS_FLG = 'N';
            S000_CALL_DDZUDRAC();
            if (pgmRtn) return;
            IBS.init(SCCGWA, DDCUCRAC);
            DDCUCRAC.AC = DCCSBALT.CARD_NO;
            DDCUCRAC.CCY = "156";
            DDCUCRAC.TX_AMT = WS_VARIABLES.AMT;
            S000_CALL_DDZUCRAC();
            if (pgmRtn) return;
        }
    }
    public void B050_ADD_DCTDCICT_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRDCICT);
        DCRDCICT.KEY.TXN_DT = SCCGWA.COMM_AREA.AC_DATE;
        DCRDCICT.KEY.TXN_JANNO = SCCGWA.COMM_AREA.JRN_NO;
        DCRDCICT.CARD_NO = DCCSBALT.CARD_NO;
        DCRDCICT.TXN_TYP = '8';
        DCRDCICT.TXN_AMT = WS_VARIABLES.AMT;
        DCRDCICT.TXN_CHNL_NO = SCCGWA.COMM_AREA.CHNL;
        DCRDCICT.TXN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DCRDCICT.TXN_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRDCICT.WRITE_CARD_STS = '0';
        DCRDCICT.TXN_STS = '0';
        DCRDCICT.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRDCICT.CRT_TLR = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        DCRDCICT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRDCICT.UPDTBL_TLR = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        T000_WRITE_DCTDCICT();
        if (pgmRtn) return;
    }
    public void B060_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT);
        WS_OUTPUT.CARD_NO = DCCSBALT.CARD_NO;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUTPUT;
        SCCFMT.DATA_LEN = 19;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_CIZQCHDC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CHDC", CICQCHDC);
        if (CICQCHDC.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQCHDC.RC);
            CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
        }
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.upd = true;
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, DCRCDDAT.KEY.CARD_NO);
            WS_COND_FLG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.TBL_FLAG = 'N';
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_CARD_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
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
    public void T000_READ_DCTINRCD() throws IOException,SQLException,Exception {
        DCTINRCD_RD = new DBParm();
        DCTINRCD_RD.TableName = "DCTINRCD";
        DCTINRCD_RD.where = "CARD_NO = :DCRINRCD.KEY.CARD_NO "
            + "AND CARD_SEQ = :DCRINRCD.KEY.CARD_SEQ";
        DCTINRCD_RD.fst = true;
        DCTINRCD_RD.order = "CRT_DATE DESC, TS DESC";
        IBS.READ(SCCGWA, DCRINRCD, this, DCTINRCD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_CARD_NOT_BACK;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTINRCD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_DCTDCICT() throws IOException,SQLException,Exception {
        DCTDCICT_RD = new DBParm();
        DCTDCICT_RD.TableName = "DCTDCICT";
        IBS.WRITE(SCCGWA, DCRDCICT, DCTDCICT_RD);
    }
    public void T000_UPDATE_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.REWRITE(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS   ", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC", DDCUCRAC);
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC", DDCUDRAC);
    }
    public void S000_CALL_DDZIQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-CCY-BAL", DDCIQBAL);
        if (DDCIQBAL.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIQBAL.RC);
            CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
        }
    }
    public void S000_CALL_DDZMTTP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-ZMTTP-TAM", DDCMTTP);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
