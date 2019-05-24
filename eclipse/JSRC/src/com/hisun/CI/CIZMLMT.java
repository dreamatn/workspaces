package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIZMLMT {
    String JIBS_tmp_str[] = new String[10];
    DBParm CITBAS_RD;
    DBParm CITLIMT_RD;
    boolean pgmRtn = false;
    double WS_CMT = 0;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCFX BPCFX = new BPCFX();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPRPRMT BPRPRMT = new BPRPRMT();
    CICPLMT CICPLMT = new CICPLMT();
    CIRBAS CIRBAS = new CIRBAS();
    CIRACR CIRACR = new CIRACR();
    CIRLIMT CIRLIMT = new CIRLIMT();
    CIRLIMT CIRLIMTO = new CIRLIMT();
    CIRLIMT CIRLIMTN = new CIRLIMT();
    int WS_AC_DATE = 0;
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICMLMT CICMLMT;
    public void MP(SCCGWA SCCGWA, CICMLMT CICMLMT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICMLMT = CICMLMT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZMLMT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICMLMT.RC);
        WS_AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        JIBS_tmp_str[2] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        JIBS_tmp_str[4] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (JIBS_tmp_str[0].equalsIgnoreCase("0801151") 
            || JIBS_tmp_str[2].equalsIgnoreCase("0801154") 
            || JIBS_tmp_str[4].equalsIgnoreCase("0801155")) {
            CEP.TRC(SCCGWA, "即期兑换不累计，直接跳过");
            Z_RET();
            if (pgmRtn) return;
        }
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICMLMT.FUNC);
        if (CICMLMT.FUNC == 'I') {
            B020_INQ_LMT_INF();
            if (pgmRtn) return;
        } else if (CICMLMT.FUNC == 'M') {
            B030_MOD_LMT_INF();
            if (pgmRtn) return;
        } else if (CICMLMT.FUNC == 'A') {
            B040_ACC_LMT_INF();
            if (pgmRtn) return;
        } else if (CICMLMT.FUNC == 'D') {
            B050_DEL_LMT_INF();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "FUNC CODE INPUT ERROR");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_FUNC_ERROR, CICMLMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICMLMT.DATA.CI_NO);
        CEP.TRC(SCCGWA, CICMLMT.DATA.LMT_TP);
        if (CICMLMT.DATA.CI_NO.trim().length() == 0 
            || CICMLMT.DATA.LMT_TP.trim().length() == 0) {
            CEP.TRC(SCCGWA, "LACK OF NORMAL INFORMATION");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, CICMLMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICMLMT.DATA.CI_NO;
        T000_READ_CITBAS_EXIST();
        if (pgmRtn) return;
    }
    public void B020_INQ_LMT_INF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICMLMT.DATA.LMT_TP);
        if ((CICMLMT.DATA.LMT_TP.equalsIgnoreCase("01") 
                || CICMLMT.DATA.LMT_TP.equalsIgnoreCase("02"))) {
            B021_INQ_LMT_INF();
            if (pgmRtn) return;
        } else if ((CICMLMT.DATA.LMT_TP.equalsIgnoreCase("03") 
                || CICMLMT.DATA.LMT_TP.equalsIgnoreCase("04"))) {
            B023_INQ_LMT_INF();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "LMT-TP INPUT ERROR");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_LMT_TP_INPUT_ERR, CICMLMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R000_TRANS_DATA_TO_OUTPUT();
        if (pgmRtn) return;
    }
    public void B021_INQ_LMT_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRLIMT);
        CIRLIMT.KEY.CI_NO = CICMLMT.DATA.CI_NO;
        CIRLIMT.KEY.LMT_TP = CICMLMT.DATA.LMT_TP;
        T000_READ_CITLIMT_01();
        if (pgmRtn) return;
    }
    public void B023_INQ_LMT_INF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICMLMT.DATA.AC_NO);
        if (CICMLMT.DATA.AC_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "AC-NO MUST INPUT WHEN LMT-DP = 03 OR 04");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, CICMLMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRLIMT);
        CIRLIMT.KEY.CI_NO = CICMLMT.DATA.CI_NO;
        CIRLIMT.KEY.AC_NO = CICMLMT.DATA.AC_NO;
        CIRLIMT.KEY.LMT_TP = CICMLMT.DATA.LMT_TP;
        T000_READ_CITLIMT_03();
        if (pgmRtn) return;
    }
    public void B030_MOD_LMT_INF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICMLMT.DATA.AMT);
        if (CICMLMT.DATA.AMT == 0) {
            CEP.TRC(SCCGWA, "MLMT-AMT MUST INPUT");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, CICMLMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, CICMLMT.DATA.LMT_TP);
        if ((CICMLMT.DATA.LMT_TP.equalsIgnoreCase("01") 
                || CICMLMT.DATA.LMT_TP.equalsIgnoreCase("02"))) {
            CEP.TRC(SCCGWA, "FOREIGN CURRENCY CANT MOD LMT");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_FUNC_ERROR, CICMLMT.RC);
            Z_RET();
            if (pgmRtn) return;
        } else if ((CICMLMT.DATA.LMT_TP.equalsIgnoreCase("03") 
                || CICMLMT.DATA.LMT_TP.equalsIgnoreCase("04"))) {
            B033_MOD_LMT_INF();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "LMT-TP INPUT ERROR");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_LMT_TP_INPUT_ERR, CICMLMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B033_MOD_LMT_INF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICMLMT.DATA.AC_NO);
        if (CICMLMT.DATA.AC_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "AC-NO MUST INPUT WHEN LMT-DP = 03 OR 04");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, CICMLMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRLIMT);
        IBS.init(SCCGWA, CIRLIMTO);
        IBS.init(SCCGWA, CIRLIMTN);
        CIRLIMT.KEY.CI_NO = CICMLMT.DATA.CI_NO;
        CIRLIMT.KEY.AC_NO = CICMLMT.DATA.AC_NO;
        CIRLIMT.KEY.LMT_TP = CICMLMT.DATA.LMT_TP;
        T000_READ_CITLIMT_UPD_03();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            IBS.CLONE(SCCGWA, CIRLIMT, CIRLIMTO);
            CIRLIMT.LMT_AMT = CICMLMT.DATA.AMT;
            if (SCCGWA.COMM_AREA.AC_DATE > CIRLIMT.UPD_DT) {
                CIRLIMT.KEY.LMT = 0;
            }
            CIRLIMT.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            CIRLIMT.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            CIRLIMT.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            T000_REWRITE_CITLIMT();
            if (pgmRtn) return;
            IBS.CLONE(SCCGWA, CIRLIMT, CIRLIMTN);
            IBS.init(SCCGWA, BPCPNHIS);
            BPCPNHIS.INFO.TX_TYP = 'M';
            R000_WRITE_HIS_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, CIRLIMT);
            CIRLIMT.KEY.CI_NO = CICMLMT.DATA.CI_NO;
            CIRLIMT.KEY.AC_NO = CICMLMT.DATA.AC_NO;
            CIRLIMT.KEY.LMT_TP = CICMLMT.DATA.LMT_TP;
            CIRLIMT.KEY.PERM_KND = CICMLMT.DATA.PERM_KND;
            CIRLIMT.LMT_AMT = CICMLMT.DATA.AMT;
            CIRLIMT.CCY = "156";
            CIRLIMT.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            CIRLIMT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            CIRLIMT.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            CIRLIMT.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
            CIRLIMT.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            CIRLIMT.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            T000_WRITE_CITLIMT();
            if (pgmRtn) return;
            IBS.CLONE(SCCGWA, CIRLIMT, CIRLIMTN);
            IBS.init(SCCGWA, BPCPNHIS);
            BPCPNHIS.INFO.TX_TYP = 'A';
            R000_WRITE_HIS_PROC();
            if (pgmRtn) return;
        }
    }
    public void B040_ACC_LMT_INF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICMLMT.DATA.CMT);
        CEP.TRC(SCCGWA, CICMLMT.DATA.CCY);
        if (CICMLMT.DATA.CMT == 0 
            || CICMLMT.DATA.CCY.trim().length() == 0) {
            CEP.TRC(SCCGWA, "LACK OF NORMAL INFORMATION");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, CICMLMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, CICMLMT.DATA.LMT_TP);
        if ((CICMLMT.DATA.LMT_TP.equalsIgnoreCase("01") 
                || CICMLMT.DATA.LMT_TP.equalsIgnoreCase("02"))) {
            B041_ACC_LMT_INF();
            if (pgmRtn) return;
        } else if ((CICMLMT.DATA.LMT_TP.equalsIgnoreCase("03") 
                || CICMLMT.DATA.LMT_TP.equalsIgnoreCase("04"))) {
            B043_ACC_LMT_INF();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "LMT-TP INPUT ERROR");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_LMT_TP_INPUT_ERR, CICMLMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R000_TRANS_DATA_TO_OUTPUT();
        if (pgmRtn) return;
    }
    public void B041_ACC_LMT_INF() throws IOException,SQLException,Exception {
        if (!CICMLMT.DATA.CCY.equalsIgnoreCase("840")) {
            IBS.init(SCCGWA, BPCFX);
            BPCFX.FUNC = '3';
            BPCFX.EXR_TYPE = "TRE";
            BPCFX.CHNL = SCCGWA.COMM_AREA.CHNL;
            BPCFX.BUY_CCY = CICMLMT.DATA.CCY;
            BPCFX.BUY_AMT = CICMLMT.DATA.CMT;
            BPCFX.SELL_CCY = "840";
            CEP.TRC(SCCGWA, CICMLMT.DATA.CCY);
            CEP.TRC(SCCGWA, CICMLMT.DATA.CMT);
            S000_CALL_BPZSFX();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCFX.SELL_AMT);
            WS_CMT = BPCFX.SELL_AMT;
        } else {
            WS_CMT = CICMLMT.DATA.CMT;
        }
        CEP.TRC(SCCGWA, WS_CMT);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            if (SCCGWA.COMM_AREA.AC_DATE != GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE) {
                Z_RET();
                if (pgmRtn) return;
            }
            WS_CMT = 0 - WS_CMT;
        }
        CEP.TRC(SCCGWA, WS_CMT);
        IBS.init(SCCGWA, CIRLIMT);
        IBS.init(SCCGWA, CIRLIMTO);
        IBS.init(SCCGWA, CIRLIMTN);
        CIRLIMT.KEY.CI_NO = CICMLMT.DATA.CI_NO;
        CIRLIMT.KEY.LMT_TP = CICMLMT.DATA.LMT_TP;
        T000_READ_CITLIMT_UPD_01();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            IBS.CLONE(SCCGWA, CIRLIMT, CIRLIMTO);
            if (SCCGWA.COMM_AREA.AC_DATE > CIRLIMT.UPD_DT) {
                CIRLIMT.KEY.LMT = WS_CMT;
            } else {
                CIRLIMT.KEY.LMT = WS_CMT + CIRLIMT.KEY.LMT;
            }
            CIRLIMT.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            CIRLIMT.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            CIRLIMT.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            T000_REWRITE_CITLIMT();
            if (pgmRtn) return;
            IBS.CLONE(SCCGWA, CIRLIMT, CIRLIMTN);
            IBS.init(SCCGWA, BPCPNHIS);
            BPCPNHIS.INFO.TX_TYP = 'M';
            R000_WRITE_HIS_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, CIRLIMT);
            CIRLIMT.KEY.CI_NO = CICMLMT.DATA.CI_NO;
            CIRLIMT.KEY.LMT_TP = CICMLMT.DATA.LMT_TP;
            CIRLIMT.KEY.PERM_KND = CICMLMT.DATA.PERM_KND;
            CIRLIMT.CCY = "840";
            CIRLIMT.KEY.LMT = WS_CMT;
            CIRLIMT.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            CIRLIMT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            CIRLIMT.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            CIRLIMT.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
            CIRLIMT.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            CIRLIMT.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            T000_WRITE_CITLIMT();
            if (pgmRtn) return;
            IBS.CLONE(SCCGWA, CIRLIMT, CIRLIMTN);
            IBS.init(SCCGWA, BPCPNHIS);
            BPCPNHIS.INFO.TX_TYP = 'A';
            R000_WRITE_HIS_PROC();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            IBS.init(SCCGWA, BPCPRMR);
            IBS.init(SCCGWA, BPRPRMT);
            IBS.init(SCCGWA, CICPLMT);
            BPCPRMR.FUNC = ' ';
            BPRPRMT.KEY.TYP = "CILMT";
            BPRPRMT.KEY.CD = CICMLMT.DATA.LMT_TP;
            CEP.TRC(SCCGWA, BPRPRMT.KEY.TYP);
            CEP.TRC(SCCGWA, BPRPRMT.KEY.CD);
            BPCPRMR.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPCPRMR.DAT_PTR = BPRPRMT;
            S000_CALL_BPZPRMR();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPRPRMT.DAT_TXT);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], CICPLMT);
            CEP.TRC(SCCGWA, CICPLMT.LMT);
            if (CICPLMT.LMT < CIRLIMT.KEY.LMT) {
                CEP.TRC(SCCGWA, "ACCUMULATED OVER LIMIT");
                CEP.ERRC(SCCGWA, CICMSG_ERROR_MSG.CI_OVER_LIMIT);
            }
        }
    }
    public void B043_ACC_LMT_INF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICMLMT.DATA.AC_NO);
        if (CICMLMT.DATA.AC_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "AC-NO MUST INPUT WHEN LMT-DP = 03 OR 04");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, CICMLMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (!CICMLMT.DATA.CCY.equalsIgnoreCase("156")) {
            CEP.TRC(SCCGWA, " CCY INPUT ERROR");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_CCY_INPUT_ERROR, CICMLMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRLIMT);
        IBS.init(SCCGWA, CIRLIMTO);
        IBS.init(SCCGWA, CIRLIMTN);
        CIRLIMT.KEY.CI_NO = CICMLMT.DATA.CI_NO;
        CIRLIMT.KEY.AC_NO = CICMLMT.DATA.AC_NO;
        CIRLIMT.KEY.LMT_TP = CICMLMT.DATA.LMT_TP;
        T000_READ_CITLIMT_UPD_03();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            IBS.CLONE(SCCGWA, CIRLIMT, CIRLIMTO);
            if (SCCGWA.COMM_AREA.AC_DATE > CIRLIMT.UPD_DT) {
                CIRLIMT.KEY.LMT = CICMLMT.DATA.CMT;
            } else {
                CIRLIMT.KEY.LMT = CICMLMT.DATA.CMT + CIRLIMT.KEY.LMT;
            }
            CIRLIMT.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            CIRLIMT.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            CIRLIMT.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            T000_REWRITE_CITLIMT();
            if (pgmRtn) return;
            IBS.CLONE(SCCGWA, CIRLIMT, CIRLIMTN);
            IBS.init(SCCGWA, BPCPNHIS);
            BPCPNHIS.INFO.TX_TYP = 'M';
            R000_WRITE_HIS_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, CIRLIMT);
            CIRLIMT.KEY.CI_NO = CICMLMT.DATA.CI_NO;
            CIRLIMT.KEY.LMT_TP = CICMLMT.DATA.LMT_TP;
            CIRLIMT.KEY.AC_NO = CICMLMT.DATA.AC_NO;
            CIRLIMT.KEY.PERM_KND = CICMLMT.DATA.PERM_KND;
            CIRLIMT.LMT_AMT = CICMLMT.DATA.AMT;
            CIRLIMT.CCY = "156";
            CIRLIMT.KEY.LMT = CICMLMT.DATA.CMT;
            CIRLIMT.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            CIRLIMT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            CIRLIMT.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            CIRLIMT.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
            CIRLIMT.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            CIRLIMT.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            T000_WRITE_CITLIMT();
            if (pgmRtn) return;
            IBS.CLONE(SCCGWA, CIRLIMT, CIRLIMTN);
            IBS.init(SCCGWA, BPCPNHIS);
            BPCPNHIS.INFO.TX_TYP = 'A';
            R000_WRITE_HIS_PROC();
            if (pgmRtn) return;
        }
    }
    public void B050_DEL_LMT_INF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICMLMT.DATA.LMT_TP);
        if ((CICMLMT.DATA.LMT_TP.equalsIgnoreCase("01") 
                || CICMLMT.DATA.LMT_TP.equalsIgnoreCase("02"))) {
            B051_DEL_LMT_INF();
            if (pgmRtn) return;
        } else if ((CICMLMT.DATA.LMT_TP.equalsIgnoreCase("03") 
                || CICMLMT.DATA.LMT_TP.equalsIgnoreCase("04"))) {
            B053_DEL_LMT_INF();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "LMT-TP INPUT ERROR");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_LMT_TP_INPUT_ERR, CICMLMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B051_DEL_LMT_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRLIMT);
        IBS.init(SCCGWA, CIRLIMTO);
        IBS.init(SCCGWA, CIRLIMTN);
        CIRLIMT.KEY.CI_NO = CICMLMT.DATA.CI_NO;
        CIRLIMT.KEY.LMT_TP = CICMLMT.DATA.LMT_TP;
        T000_READ_CITLIMT_UPD_01();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "LMT INF NOT EXIST");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_LMT_INF_NOT_EXIST, CICMLMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, CIRLIMT, CIRLIMTO);
        CIRLIMT.EXP_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRLIMT.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRLIMT.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRLIMT.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_REWRITE_CITLIMT();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRLIMT, CIRLIMTN);
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        R000_WRITE_HIS_PROC();
        if (pgmRtn) return;
    }
    public void B053_DEL_LMT_INF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICMLMT.DATA.AC_NO);
        if (CICMLMT.DATA.AC_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "AC-NO MUST INPUT WHEN LMT-DP = 03 OR 04");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, CICMLMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRLIMT);
        IBS.init(SCCGWA, CIRLIMTO);
        IBS.init(SCCGWA, CIRLIMTN);
        CIRLIMT.KEY.CI_NO = CICMLMT.DATA.CI_NO;
        CIRLIMT.KEY.LMT_TP = CICMLMT.DATA.LMT_TP;
        CIRLIMT.KEY.AC_NO = CICMLMT.DATA.AC_NO;
        T000_READ_CITLIMT_UPD_03();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "LMT INF NOT EXIST");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_LMT_INF_NOT_EXIST, CICMLMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, CIRLIMT, CIRLIMTO);
        CIRLIMT.EXP_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRLIMT.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRLIMT.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRLIMT.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_REWRITE_CITLIMT();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRLIMT, CIRLIMTN);
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        R000_WRITE_HIS_PROC();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA_TO_OUTPUT() throws IOException,SQLException,Exception {
        CICMLMT.DATA.PERM_KND = CIRLIMT.KEY.PERM_KND;
        CICMLMT.DATA.LMT = CIRLIMT.KEY.LMT;
        CICMLMT.DATA.CCY = CIRLIMT.CCY;
        CICMLMT.DATA.AMT = CIRLIMT.LMT_AMT;
    }
    public void R000_WRITE_HIS_PROC() throws IOException,SQLException,Exception {
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.FMT_ID = "CIRLIMT";
        BPCPNHIS.INFO.FMT_ID_LEN = 288;
        BPCPNHIS.INFO.CI_NO = CICMLMT.DATA.CI_NO;
        BPCPNHIS.INFO.OLD_DAT_PT = CIRLIMTO;
        BPCPNHIS.INFO.NEW_DAT_PT = CIRLIMTN;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPNHIS.RC);
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            CEP.ERR(SCCGWA, BPCPRMR.RC);
        }
    }
    public void S000_CALL_BPZSFX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-FX", BPCFX);
        if (BPCFX.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, BPCFX.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFX.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], CICMLMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITBAS_EXIST() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "LMT INF NOT EXIST");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND, CICMLMT.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITBAS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITLIMT_01() throws IOException,SQLException,Exception {
        CITLIMT_RD = new DBParm();
        CITLIMT_RD.TableName = "CITLIMT";
        CITLIMT_RD.eqWhere = "CI_NO , LMT_TP";
        CITLIMT_RD.where = "EXP_DT = 0 "
            + "OR EXP_DT > :WS_AC_DATE";
        IBS.READ(SCCGWA, CIRLIMT, this, CITLIMT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "LMT INF NOT EXIST");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_LMT_INF_NOT_EXIST, CICMLMT.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITLIMT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITLIMT_03() throws IOException,SQLException,Exception {
        CITLIMT_RD = new DBParm();
        CITLIMT_RD.TableName = "CITLIMT";
        CITLIMT_RD.eqWhere = "CI_NO , LMT_TP , AC_NO";
        CITLIMT_RD.where = "EXP_DT = 0 "
            + "OR EXP_DT > :WS_AC_DATE";
        IBS.READ(SCCGWA, CIRLIMT, this, CITLIMT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "LMT INF NOT EXIST");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_LMT_INF_NOT_EXIST, CICMLMT.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITLIMT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITLIMT_UPD_01() throws IOException,SQLException,Exception {
        CITLIMT_RD = new DBParm();
        CITLIMT_RD.TableName = "CITLIMT";
        CITLIMT_RD.eqWhere = "CI_NO , LMT_TP";
        CITLIMT_RD.where = "EXP_DT = 0 "
            + "OR EXP_DT > :WS_AC_DATE";
        CITLIMT_RD.upd = true;
        IBS.READ(SCCGWA, CIRLIMT, this, CITLIMT_RD);
    }
    public void T000_READ_CITLIMT_UPD_03() throws IOException,SQLException,Exception {
        CITLIMT_RD = new DBParm();
        CITLIMT_RD.TableName = "CITLIMT";
        CITLIMT_RD.eqWhere = "CI_NO , LMT_TP , AC_NO";
        CITLIMT_RD.where = "EXP_DT = 0 "
            + "OR EXP_DT > :WS_AC_DATE";
        CITLIMT_RD.upd = true;
        IBS.READ(SCCGWA, CIRLIMT, this, CITLIMT_RD);
    }
    public void T000_WRITE_CITLIMT() throws IOException,SQLException,Exception {
        CITLIMT_RD = new DBParm();
        CITLIMT_RD.TableName = "CITLIMT";
        IBS.WRITE(SCCGWA, CIRLIMT, CITLIMT_RD);
    }
    public void T000_REWRITE_CITLIMT() throws IOException,SQLException,Exception {
        CITLIMT_RD = new DBParm();
        CITLIMT_RD.TableName = "CITLIMT";
        IBS.REWRITE(SCCGWA, CIRLIMT, CITLIMT_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
