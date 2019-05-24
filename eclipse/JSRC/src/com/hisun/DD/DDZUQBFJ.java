package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZUQBFJ {
    int JIBS_tmp_int;
    DBParm DDTMST_RD;
    DBParm DDTCCY_RD;
    DBParm DDTRSAC_RD;
    int WS_CNT = 0;
    double WS_TOT_AMT = 0;
    double WS_TOTAL_AMT1 = 0;
    char WS_WHITE_FLG = ' ';
    char WS_BLACK_FLG = ' ';
    char WS_GREY_FLG = ' ';
    char WS_DDTRSAC_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCFX BPCFX = new BPCFX();
    DDRRSAC DDRRSAC = new DDRRSAC();
    DDRCCY DDRCCY = new DDRCCY();
    DDRMST DDRMST = new DDRMST();
    CICQCIAC CICQCIAC = new CICQCIAC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DDCUQBFJ DDCUQBFJ;
    public void MP(SCCGWA SCCGWA, DDCUQBFJ DDCUQBFJ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCUQBFJ = DDCUQBFJ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDZUQBFJ return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, DDCUQBFJ.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (DDCUQBFJ.INP_DATA.QBFJFUNC == 'C') {
            B020_CHK_LMT_INF();
        } else {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_INPUT_ERR);
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DDCUQBFJ.INP_DATA.CI_NO.trim().length() == 0 
            || DDCUQBFJ.INP_DATA.OTH_AC.trim().length() == 0 
            || DDCUQBFJ.INP_DATA.AC_TYPE.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_INPUT_ERR);
        }
    }
    public void B020_CHK_LMT_INF() throws IOException,SQLException,Exception {
        WS_BLACK_FLG = 'N';
        WS_WHITE_FLG = 'N';
        WS_GREY_FLG = 'N';
        IBS.init(SCCGWA, DDRRSAC);
        DDRRSAC.KEY.TYPE = '2';
        DDRRSAC.KEY.CTL_TYPE = '3';
        DDRRSAC.KEY.OTH_AC = DDCUQBFJ.INP_DATA.OTH_AC;
        DDRRSAC.KEY.AC_TYPE = DDCUQBFJ.INP_DATA.AC_TYPE;
        T000_READUPD_DDTRSAC();
        if (WS_DDTRSAC_FLG == 'Y') {
            WS_BLACK_FLG = 'Y';
            R000_CHECK_OTHAC_LIMT();
            T000_REWRITE_DDTRSAC();
        }
        IBS.init(SCCGWA, DDRRSAC);
        DDRRSAC.KEY.TYPE = '2';
        DDRRSAC.KEY.CTL_TYPE = '2';
        DDRRSAC.KEY.OTH_AC = DDCUQBFJ.INP_DATA.OTH_AC;
        DDRRSAC.KEY.AC_TYPE = DDCUQBFJ.INP_DATA.AC_TYPE;
        T000_READUPD_DDTRSAC();
        if (WS_DDTRSAC_FLG == 'Y') {
            WS_WHITE_FLG = 'Y';
            R000_CHECK_OTHAC_LIMT();
            T000_REWRITE_DDTRSAC();
        }
        R000_CHECK_BFJCI_LIMIT();
        if (WS_WHITE_FLG == 'N' 
            && WS_BLACK_FLG == 'N') {
            WS_GREY_FLG = 'Y';
            R000_CHECK_OTHAC_LIMT();
            T000_REWRITE_DDTRSAC();
        }
    }
    public void R000_CHECK_OTHAC_LIMT() throws IOException,SQLException,Exception {
        if ((WS_WHITE_FLG == 'Y' 
            || WS_GREY_FLG == 'N') 
            && DDCUQBFJ.INP_DATA.TX_AMT > DDRRSAC.SINGAL_LMT) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_OVER_SINGAL_LIMIT);
        }
        if (WS_WHITE_FLG == 'Y' 
            || WS_GREY_FLG == 'Y' 
            || WS_BLACK_FLG == 'Y') {
            DDRRSAC.DAY_TOT_AMT = DDRRSAC.DAY_TOT_AMT + DDCUQBFJ.INP_DATA.TX_AMT;
            if (DDRRSAC.DAY_TOT_AMT > DDRRSAC.DAY_AMT_LMT) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_OVER_DAY_AMT_LIMT);
            }
        }
        if (WS_GREY_FLG == 'Y') {
            DDRRSAC.DAY_TOT_CNT = DDRRSAC.DAY_TOT_CNT + 1;
            if (DDRRSAC.DAY_TOT_CNT > DDRRSAC.DAY_CNT_LMT) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_OVER_DAY_CNT_LIMT);
            }
        }
        if (WS_GREY_FLG == 'Y') {
            DDRRSAC.MON_TOT_AMT = DDRRSAC.MON_TOT_AMT + DDCUQBFJ.INP_DATA.TX_AMT;
            if (DDRRSAC.MON_TOT_AMT > DDRRSAC.MON_AMT_LMT) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_OVER_MON_AMT_LIMT);
            }
        }
        DDRRSAC.MON_TOT_CNT = DDRRSAC.MON_TOT_CNT + 1;
        if (WS_GREY_FLG == 'Y') {
            DDRRSAC.MON_TOT_CNT = DDRRSAC.MON_TOT_CNT + 1;
            if (DDRRSAC.MON_TOT_CNT > DDRRSAC.MON_CNT_LMT) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_OVER_MON_CNT_LIMT);
            }
        }
    }
    public void R000_CHECK_BFJCI_LIMIT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRRSAC);
        DDRRSAC.KEY.AC_TYPE = DDCUQBFJ.INP_DATA.AC_TYPE;
        DDRRSAC.KEY.TYPE = '0';
        DDRRSAC.KEY.CTL_TYPE = '1';
        T000_READUPD_DDTRSAC();
        IBS.init(SCCGWA, CICQCIAC);
        CICQCIAC.FUNC = '1';
        CICQCIAC.DATA.CI_NO = DDCUQBFJ.INP_DATA.CI_NO;
        CICQCIAC.DATA.FRM_APP = "DD";
        S000_CALL_CIZQCIAC();
        for (WS_CNT = 1; CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_CNT-1].ENTY_NO.trim().length() != 0 
            && WS_CNT <= 100; WS_CNT += 1) {
            IBS.init(SCCGWA, DDRMST);
            DDRMST.KEY.CUS_AC = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_CNT-1].ENTY_NO;
            T000_READ_DDTMST();
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS != 'C' 
                && DDRMST.AC_STS_WORD.substring(68 - 1, 68 + 1 - 1).equalsIgnoreCase(DDRRSAC.KEY.AC_TYPE)) {
                IBS.init(SCCGWA, DDRCCY);
                DDRCCY.CUS_AC = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_CNT-1].ENTY_NO;
                T000_READ_DDTCCY_FIRST();
                WS_TOT_AMT = WS_TOT_AMT + DDRCCY.CURR_BAL;
            }
        }
        WS_TOTAL_AMT1 = WS_TOT_AMT - DDCUQBFJ.INP_DATA.TX_AMT;
        if (WS_TOTAL_AMT1 < DDRRSAC.BASE_AMT) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_LESS_THEN_LIMIT);
        }
    }
    public void S000_CALL_CIZQCIAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST-ACR", CICQCIAC);
        if (CICQCIAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQCIAC.RC);
        }
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
    }
    public void T000_READ_DDTCCY_FIRST() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.where = "CUS_AC = :DDRCCY.CUS_AC";
        DDTCCY_RD.fst = true;
        IBS.READ(SCCGWA, DDRCCY, this, DDTCCY_RD);
    }
    public void T000_READUPD_DDTRSAC() throws IOException,SQLException,Exception {
        WS_DDTRSAC_FLG = 'N';
        DDTRSAC_RD = new DBParm();
        DDTRSAC_RD.TableName = "DDTRSAC";
        DDTRSAC_RD.upd = true;
        IBS.READ(SCCGWA, DDRRSAC, DDTRSAC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTRSAC_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_DDTRSAC_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTRSAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_REWRITE_DDTRSAC() throws IOException,SQLException,Exception {
        DDRRSAC.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRRSAC.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTRSAC_RD = new DBParm();
        DDTRSAC_RD.TableName = "DDTRSAC";
        IBS.REWRITE(SCCGWA, DDRRSAC, DDTRSAC_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
