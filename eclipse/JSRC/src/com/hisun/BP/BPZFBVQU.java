package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFBVQU {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG";
    String CPN_PARM_READ = "BP-PARM-READ        ";
    String CPN_REC_BPRD = "BP-R-BRW-BVPRD      ";
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    int WS_BR = 0;
    int WS_BR1 = 0;
    BPZFBVQU_WS_BPRD_KEY WS_BPRD_KEY = new BPZFBVQU_WS_BPRD_KEY();
    char WS_TOP_BR_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRPBPRD BPRPBPRD = new BPRPBPRD();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCRBPRD BPCRBPRD = new BPCRBPRD();
    BPRBPRD BPRBPRD = new BPRBPRD();
    SCCGWA SCCGWA;
    BPCFBVQU BPCFBVQU;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BPCPORUP_DATA_INFO BPCPORUP;
    public void MP(SCCGWA SCCGWA, BPCFBVQU BPCFBVQU) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFBVQU = BPCFBVQU;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.CNT_UT);
        CEP.TRC(SCCGWA, "BPZFBVQU return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        BPCFBVQU.RC.RC_MMO = SCCGWA.COMM_AREA.AP_MMO;
        BPCFBVQU.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCFBVQU.TX_DATA.KEY.CODE.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BVCD_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.KEY.BR);
        CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.BR);
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.KEY.CODE);
        if (BPCFBVQU.TX_DATA.KEY.BR == 0) {
            B100_QUERY_PROC_01();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.KEY.BR);
            CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.BR);
            if (BPCFBVQU.TX_DATA.KEY.BR == BPCPORUP.DATA_INFO.BR) {
                WS_BR = BPCFBVQU.TX_DATA.KEY.BR;
                WS_BR1 = BPCPORUP.DATA_INFO.BR;
                B100_QUERY_PROC_01();
                if (pgmRtn) return;
            } else {
                B100_QUERY_PROC_02();
                if (pgmRtn) return;
            }
        }
        B200_RET_QINF();
        if (pgmRtn) return;
    }
    public void B100_QUERY_PROC_01() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRBPRD);
        IBS.init(SCCGWA, BPRBPRD);
        WS_BPRD_KEY.WS_BPRD_CODE = BPCFBVQU.TX_DATA.KEY.CODE;
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.KEY.BR);
        if (BPCFBVQU.TX_DATA.KEY.BR == 0) {
            CEP.TRC(SCCGWA, "NCB02261013");
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
            WS_BPRD_KEY.WS_BPRD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        } else {
            WS_BPRD_KEY.WS_BPRD_BR = BPCFBVQU.TX_DATA.KEY.BR;
        }
        if (BPCFBVQU.TX_DATA.IBS_AC_BK.trim().length() == 0) {
            CEP.TRC(SCCGWA, "11111");
            BPRBPRD.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        } else {
            CEP.TRC(SCCGWA, "22222");
            BPRBPRD.KEY.IBS_AC_BK = BPCFBVQU.TX_DATA.IBS_AC_BK;
        }
        BPRBPRD.KEY.CODE = IBS.CLS2CPY(SCCGWA, WS_BPRD_KEY);
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.IBS_AC_BK);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_BANK);
        CEP.TRC(SCCGWA, BPRBPRD.KEY.IBS_AC_BK);
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.KEY.CODE);
        CEP.TRC(SCCGWA, BPRBPRD.KEY.CODE);
        BPCRBPRD.INFO.FUNC = 'R';
        BPCRBPRD.INFO.POINTER = BPRBPRD;
        BPCRBPRD.INFO.LEN = 512;
        S000_CALL_BPZRBPRD();
        if (pgmRtn) return;
        if (BPCRBPRD.RETURN_INFO == 'N') {
            for (WS_I = 1; WS_I <= BPCPORUP.DATA_INFO.CNT 
                && BPCRBPRD.RETURN_INFO != 'F'; WS_I += 1) {
                IBS.init(SCCGWA, WS_BPRD_KEY);
                IBS.init(SCCGWA, BPRBPRD);
                IBS.init(SCCGWA, BPCRBPRD);
                WS_BPRD_KEY.WS_BPRD_CODE = BPCFBVQU.TX_DATA.KEY.CODE;
                WS_BPRD_KEY.WS_BPRD_BR = BPCPORUP.DATA_INFO.SUPR_GRP[WS_I-1].SUPR_BR;
                CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.KEY.CODE);
                CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.CNT);
                CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.SUPR_GRP[WS_I-1].SUPR_BR);
                IBS.init(SCCGWA, BPRBPRD);
                IBS.init(SCCGWA, BPCRBPRD);
                if (BPCFBVQU.TX_DATA.IBS_AC_BK.trim().length() == 0) {
                    BPRBPRD.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
                } else {
                    BPRBPRD.KEY.IBS_AC_BK = BPCFBVQU.TX_DATA.IBS_AC_BK;
                }
                BPRBPRD.KEY.CODE = IBS.CLS2CPY(SCCGWA, WS_BPRD_KEY);
                BPCRBPRD.INFO.FUNC = 'R';
                CEP.TRC(SCCGWA, BPRBPRD.KEY.CODE);
                BPCRBPRD.INFO.POINTER = BPRBPRD;
                BPCRBPRD.INFO.LEN = 512;
                S000_CALL_BPZRBPRD();
                if (pgmRtn) return;
            }
        }
    }
    public void B100_QUERY_PROC_02() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRBPRD);
        IBS.init(SCCGWA, BPRBPRD);
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.SUPR_BR = BPCFBVQU.TX_DATA.KEY.BR;
        WS_TOP_BR_FLG = 'N';
        WS_I = 0;
        for (WS_I = 1; WS_I <= BPCPORUP.DATA_INFO.CNT 
            && BPCPRMR.RC.RC_RTNCODE != 0; WS_I += 1) {
            IBS.init(SCCGWA, BPCRBPRD);
            IBS.init(SCCGWA, BPRBPRD);
            WS_BPRD_KEY.WS_BPRD_CODE = BPCFBVQU.TX_DATA.KEY.CODE;
            WS_BPRD_KEY.WS_BPRD_BR = BPCPQORG.SUPR_BR;
            BPRBPRD.KEY.CODE = IBS.CLS2CPY(SCCGWA, WS_BPRD_KEY);
            BPCRBPRD.INFO.FUNC = 'R';
            BPCRBPRD.INFO.POINTER = BPRBPRD;
            BPCRBPRD.INFO.LEN = 512;
            S000_CALL_BPZRBPRD();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPQORG.SUPR_BR);
            BPCPQORG.BR = BPCPQORG.SUPR_BR;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
        }
    }
    public void B200_RET_QINF() throws IOException,SQLException,Exception {
        if (BPCRBPRD.RETURN_INFO != 'F') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_BPRD_NOTFND, BPCFBVQU.RC);
        }
        BPCFBVQU.TX_DATA.TYPE = BPRBPRD.TYPE;
        CEP.TRC(SCCGWA, BPRBPRD.TYPE);
        if (BPRBPRD.KEY.CODE == null) BPRBPRD.KEY.CODE = "";
        JIBS_tmp_int = BPRBPRD.KEY.CODE.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRBPRD.KEY.CODE += " ";
        if (BPRBPRD.KEY.CODE.substring(0, 6).trim().length() == 0) BPCFBVQU.TX_DATA.KEY.BR = 0;
        else BPCFBVQU.TX_DATA.KEY.BR = Integer.parseInt(BPRBPRD.KEY.CODE.substring(0, 6));
        CEP.TRC(SCCGWA, BPRBPRD.KEY.CODE);
        BPCFBVQU.TX_DATA.BV_CNM = BPRBPRD.BV_CNM;
        CEP.TRC(SCCGWA, BPRBPRD.BV_CNM);
        BPCFBVQU.TX_DATA.BV_ENM = BPRBPRD.BV_ENM;
        CEP.TRC(SCCGWA, BPRBPRD.BV_ENM);
        BPCFBVQU.TX_DATA.BV_CNMS = BPRBPRD.BV_CNMS;
        BPCFBVQU.TX_DATA.BV_ENMS = BPRBPRD.BV_ENMS;
        BPCFBVQU.TX_DATA.BV_CFLG = BPRBPRD.BV_CFLG;
        BPCFBVQU.TX_DATA.LEVEL = BPRBPRD.LEVEL;
        BPCFBVQU.TX_DATA.HEAD_LENGTH = BPRBPRD.HEAD_LENGTH;
        BPCFBVQU.TX_DATA.NO_LENGTH = BPRBPRD.NO_LENGTH;
        CEP.TRC(SCCGWA, BPRBPRD.NO_LENGTH);
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.NO_LENGTH);
        BPCFBVQU.TX_DATA.USE_MODE = BPRBPRD.USE_MODE;
        BPCFBVQU.TX_DATA.USE_CTL = BPRBPRD.USE_CTL;
        BPCFBVQU.TX_DATA.CTL_FLG = BPRBPRD.CTL_FLG;
        BPCFBVQU.TX_DATA.CNT_FLG = BPRBPRD.CNT_FLG;
        BPCFBVQU.TX_DATA.CNT_UT = BPRBPRD.CNT_UT;
        BPCFBVQU.TX_DATA.COST_PRICE = BPRBPRD.COST_PRICE;
        BPCFBVQU.TX_DATA.SER_CHARGE = BPRBPRD.SER_CHARGE;
        BPCFBVQU.TX_DATA.CCY = BPRBPRD.CCY;
        BPCFBVQU.TX_DATA.AC_TYP = BPRBPRD.AC_TYP;
        BPCFBVQU.TX_DATA.CUT_FLG = BPRBPRD.CUT_FLG;
        BPCFBVQU.TX_DATA.OUT_FLG = BPRBPRD.OUT_FLG;
        BPCFBVQU.TX_DATA.SELL_FLG = BPRBPRD.SELL_FLG;
        BPCFBVQU.TX_DATA.AUTO_REL_DAY = BPRBPRD.AUTO_REL_DAY;
        BPCFBVQU.TX_DATA.PP_FLG = BPRBPRD.PP_FLG;
        BPCFBVQU.TX_DATA.VAL_DAY = BPRBPRD.VAL_DAY;
        BPCFBVQU.TX_DATA.B_LMT = BPRBPRD.B_LMT_L;
        BPCFBVQU.TX_DATA.V_LMT = BPRBPRD.V_LMT_L;
        BPCFBVQU.TX_DATA.B_LMTUP = BPRBPRD.B_LMT_U;
        BPCFBVQU.TX_DATA.V_LMTUP = BPRBPRD.V_LMT_U;
        BPCFBVQU.TX_DATA.STS = BPRBPRD.STS;
        BPCFBVQU.TX_DATA.BV_RANGE = BPRBPRD.BV_RANGE;
        BPCFBVQU.TX_DATA.CHK_FLG = BPRBPRD.CHK_FLG;
        BPCFBVQU.TX_DATA.BV_SRC = BPRBPRD.BV_SRC;
        BPCFBVQU.TX_DATA.STR_POS = BPRBPRD.STR_POS;
        BPCFBVQU.TX_DATA.REL_BV = BPRBPRD.REL_BV;
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_PARM_READ, BPCPRMR);
        CEP.TRC(SCCGWA, BPCPRMR.RC);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
        if (BPCPRMR.RC.RC_RTNCODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFBVQU.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRBPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_BPRD, BPCRBPRD);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCFBVQU.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCFBVQU = ");
            CEP.TRC(SCCGWA, BPCFBVQU);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
