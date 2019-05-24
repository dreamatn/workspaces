package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.BP.*;
import com.hisun.DD.*;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSBAPH {
    String JIBS_tmp_str[] = new String[10];
    DBParm DCTCDDAT_RD;
    DBParm DCTINRCD_RD;
    DBParm DCTDCICT_RD;
    boolean pgmRtn = false;
    String OUTPUT_FMT = "DC332";
    DCZSBAPH_WS_VARIABLES WS_VARIABLES = new DCZSBAPH_WS_VARIABLES();
    DCZSBAPH_WS_OUTPUT WS_OUTPUT = new DCZSBAPH_WS_OUTPUT();
    char WS_CARD_FLG = ' ';
    DCZSBAPH_WS_COND_FLG WS_COND_FLG = new DCZSBAPH_WS_COND_FLG();
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
    DCRCTCDC DCRCTCDC = new DCRCTCDC();
    CICQCHDC CICQCHDC = new CICQCHDC();
    CICQACAC CICQACAC = new CICQACAC();
    DDCMTTP DDCMTTP = new DDCMTTP();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    DCCSBAPH DCCSBAPH;
    public void MP(SCCGWA SCCGWA, DCCSBAPH DCCSBAPH) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSBAPH = DCCSBAPH;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSBAPH return!");
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
        B045_REGIST_DCTDCICT();
        if (pgmRtn) return;
        B050_OUTPUT_PROCESS();
        if (pgmRtn) return;
        B060_HISTORY_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSBAPH.IO_AREA.CARD_NO);
        if (DCCSBAPH.IO_AREA.CARD_NO.trim().length() == 0) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_MUST_INPUT_CARD_NO;
            CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
        }
        DCRCDDAT.KEY.CARD_NO = DCCSBAPH.IO_AREA.CARD_NO;
        T000_READ_DCTCDDAT();
        if (pgmRtn) return;
        if (DCRCDDAT.CARD_STS != 'N') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_CARD_NOT_NORMAL_STS;
            CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
        }
        CEP.TRC(SCCGWA, DCCSBAPH.IO_AREA.CRD_BAL_AMT);
        CEP.TRC(SCCGWA, DCCSBAPH.IO_AREA.BAL_PAT_AMT);
        WS_VARIABLES.TOTAL_CARD_AMT = DCCSBAPH.IO_AREA.BAL_PAT_AMT + DCCSBAPH.IO_AREA.CRD_BAL_AMT;
        if (WS_VARIABLES.TOTAL_CARD_AMT > 1000) {
            CEP.ERR(SCCGWA, ERROR_MSG.DC_QC_OVER_MAX_AMT);
        }
    }
    public void B030_READ_DCTINRCD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRINRCD);
        DCRINRCD.NEW_CARD_NO = DCCSBAPH.IO_AREA.CARD_NO;
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
    }
    public void B040_BALANCE_AMT_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'I';
        CICQACAC.DATA.AGR_NO = DCCSBAPH.IO_AREA.CARD_NO;
        CICQACAC.DATA.AID = "003";
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        IBS.init(SCCGWA, DDCMTTP);
        DDCMTTP.FUNC = '1';
        DDCMTTP.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDCMTTP.TAMT = DCCSBAPH.IO_AREA.BAL_PAT_AMT;
        S000_CALL_DDZMTTP();
        if (pgmRtn) return;
    }
    public void B045_REGIST_DCTDCICT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRDCICT);
        DCRDCICT.KEY.TXN_DT = SCCGWA.COMM_AREA.AC_DATE;
        DCRDCICT.KEY.TXN_JANNO = SCCGWA.COMM_AREA.JRN_NO;
        DCRDCICT.CARD_NO = DCCSBAPH.IO_AREA.CARD_NO;
        DCRDCICT.TXN_TYP = '3';
        DCRDCICT.TXN_AMT = DCCSBAPH.IO_AREA.BAL_PAT_AMT;
        DCRDCICT.BEF_TXN_AMT = DCCSBAPH.IO_AREA.CRD_BAL_AMT;
        DCRDCICT.TXN_CHNL_NO = SCCGWA.COMM_AREA.CHNL;
        DCRDCICT.TXN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DCRDCICT.TXN_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRDCICT.TXN_STS = '0';
        DCRDCICT.TO_AC_NO = DCCSBAPH.IO_AREA.CARD_NO;
        DCRDCICT.WRITE_CARD_STS = '1';
        DCRDCICT.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRDCICT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_DCTDCICT();
        if (pgmRtn) return;
    }
    public void B050_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT);
        WS_OUTPUT.CARD_NO = DCCSBAPH.IO_AREA.CARD_NO;
        WS_OUTPUT.TRADE_STS = '1';
        WS_OUTPUT.TRD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        WS_OUTPUT.TRD_SEQNO = SCCGWA.COMM_AREA.JRN_NO;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUTPUT;
        SCCFMT.DATA_LEN = 40;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B060_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCGTSCD);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCSBAPH);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCGTSCD);
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.TX_RMK = "BALANCE PATCH";
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID = "DCCSBAPH";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_TOOL = DCCSBAPH.IO_AREA.CARD_NO;
        BPCPNHIS.INFO.CI_NO = DCRCDDAT.CARD_OWN_CINO;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        BPCPNHIS.INFO.FMT_ID_LEN = 55;
        BPCPNHIS.INFO.NEW_DAT_PT = DCCGTSCD;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_CIZQCHDC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CHDC", CICQCHDC);
        if (CICQCHDC.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQCHDC.RC);
            CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZMTTP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-ZMTTP-TAM", DDCMTTP);
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
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
        DCTINRCD_RD.where = "NEW_CARD_NO = :DCRINRCD.NEW_CARD_NO";
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
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS   ", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
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
