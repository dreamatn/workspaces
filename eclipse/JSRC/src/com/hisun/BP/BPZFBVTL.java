package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFBVTL {
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String CPN_R_BRW_TBVD = "BP-R-BRW-TBVD";
    String CPN_R_MGM_TBV = "BP-R-MGM-TBV";
    String CPN_R_BRW_BMOV = "BP-R-BRW-BMOV";
    String CPN_R_BPTVHPB_MTN = "BP-R-BPTVHPB-MTN";
    String WS_ERR_MSG = " ";
    int WS_TLR_BR = 0;
    char WS_BOX_FLG = ' ';
    char WS_LIB_FLG = ' ';
    String WS_BOX_NO = " ";
    int WS_CNT = 0;
    String WS_LIB_NO = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRVHPB BPRVHPB = new BPRVHPB();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCRTBDB BPCRTBDB = new BPCRTBDB();
    BPCRTBV BPCRTBV = new BPCRTBV();
    BPCRBMOB BPCRBMOB = new BPCRBMOB();
    BPCRVHPB BPCRVHPB = new BPCRVHPB();
    BPRTBVD BPRTBVD = new BPRTBVD();
    BPRTBV BPRTBV = new BPRTBV();
    BPRBMOV BPRBMOV = new BPRBMOV();
    SCCGWA SCCGWA;
    BPCFBVTL BPCFBVTL;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCFBVTL BPCFBVTL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFBVTL = BPCFBVTL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZFBVTL return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCFBVTL.RC.RC_MMO = SCCGWA.COMM_AREA.AP_MMO;
        BPCFBVTL.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHK_TLR_VALID();
        B010_QUERY_BOXNO();
        B100_CHK_BAL_PROC();
        B300_CHK_ONWAY_PROC();
    }
    public void B000_CHK_TLR_VALID() throws IOException,SQLException,Exception {
        if (BPCFBVTL.TLR.trim().length() == 0) {
            BPCFBVTL.TLR = SCCGWA.COMM_AREA.TL_ID;
            WS_TLR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        } else {
            IBS.init(SCCGWA, BPCFTLRQ);
            BPCFTLRQ.INFO.TLR = BPCFBVTL.TLR;
            S000_CALL_BPZFTLRQ();
            if (BPCFTLRQ.RC.RC_CODE != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
                S000_ERR_MSG_PROC();
            }
            WS_TLR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        }
    }
    public void B010_QUERY_BOXNO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRVHPB);
        IBS.init(SCCGWA, BPCRVHPB);
        BPRVHPB.CUR_TLR = BPCFBVTL.TLR;
        CEP.TRC(SCCGWA, BPRVHPB.BR);
        CEP.TRC(SCCGWA, BPRVHPB.CUR_TLR);
        CEP.TRC(SCCGWA, BPRVHPB.POLL_BOX_IND);
        CEP.TRC(SCCGWA, BPRVHPB.STS);
        BPCRVHPB.INFO.FUNC = 'B';
        S000_CALL_BPZRVHPB();
        BPCRVHPB.INFO.FUNC = 'N';
        S000_CALL_BPZRVHPB();
        while (BPCRVHPB.RETURN_INFO != 'N' 
            && WS_CNT <= 1000) {
            WS_CNT += 1;
            if (BPRVHPB.POLL_BOX_IND == '0') {
                WS_BOX_FLG = '1';
                WS_BOX_NO = BPRVHPB.KEY.POOL_BOX_NO;
                BPCFBVTL.BOX_BR = BPRVHPB.BR;
                if (BPRVHPB.BV_CHK_FLG == 'N') {
                    BPCFBVTL.BV_BCHK_FLG = 'N';
                } else {
                    BPCFBVTL.BV_BCHK_FLG = 'Y';
                }
                if (BPCFBVTL.DELETE_FLG == 'Y' 
                    && BPRVHPB.RELATE_FLG == 'Y') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_HAD_BVBOX;
                    S000_ERR_MSG_PROC();
                }
            } else {
                WS_LIB_FLG = '1';
                WS_LIB_NO = BPRVHPB.KEY.POOL_BOX_NO;
                BPCFBVTL.LIB_BR = BPRVHPB.BR;
                if (BPRVHPB.BV_CHK_FLG == 'N') {
                    BPCFBVTL.BV_VCHK_FLG = 'N';
                } else {
                    BPCFBVTL.BV_VCHK_FLG = 'Y';
                }
                if (BPCFBVTL.DELETE_FLG == 'Y' 
                    && BPRVHPB.RELATE_FLG == 'Y') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_HAD_BVLIB;
                    S000_ERR_MSG_PROC();
                }
            }
            BPCRVHPB.INFO.FUNC = 'N';
            S000_CALL_BPZRVHPB();
        }
        BPCRVHPB.INFO.FUNC = 'E';
        S000_CALL_BPZRVHPB();
    }
    public void B100_CHK_BAL_PROC() throws IOException,SQLException,Exception {
        if (WS_BOX_FLG == '1') {
            IBS.init(SCCGWA, BPRTBVD);
            BPRTBVD.KEY.BR = WS_TLR_BR;
            BPRTBVD.KEY.PL_BOX_NO = WS_BOX_NO;
            BPRTBVD.TYPE = '0';
            IBS.init(SCCGWA, BPCRTBDB);
            BPCRTBDB.INFO.FUNC = '6';
            S000_CALL_BPZRTBDB();
            IBS.init(SCCGWA, BPCRTBDB);
            BPCRTBDB.INFO.FUNC = 'N';
            S000_CALL_BPZRTBDB();
            if (BPCRTBDB.RETURN_INFO == 'N') {
                BPCFBVTL.BV_BBAL_FLG = 'N';
            } else {
                BPCFBVTL.BV_BBAL_FLG = 'Y';
            }
            IBS.init(SCCGWA, BPCRTBDB);
            BPCRTBDB.INFO.FUNC = 'E';
            S000_CALL_BPZRTBDB();
        }
        if (WS_LIB_FLG == '1') {
            IBS.init(SCCGWA, BPRTBVD);
            BPRTBVD.KEY.BR = WS_TLR_BR;
            BPRTBVD.KEY.PL_BOX_NO = WS_BOX_NO;
            BPRTBVD.TYPE = '0';
            IBS.init(SCCGWA, BPCRTBDB);
            BPCRTBDB.INFO.FUNC = '6';
            S000_CALL_BPZRTBDB();
            IBS.init(SCCGWA, BPCRTBDB);
            BPCRTBDB.INFO.FUNC = 'N';
            S000_CALL_BPZRTBDB();
            if (BPCRTBDB.RETURN_INFO == 'N') {
                BPCFBVTL.BV_VBAL_FLG = 'N';
            } else {
                BPCFBVTL.BV_VBAL_FLG = 'Y';
            }
            IBS.init(SCCGWA, BPCRTBDB);
            BPCRTBDB.INFO.FUNC = 'E';
            S000_CALL_BPZRTBDB();
        }
    }
    public void B200_CHK_CHKED_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTBV);
        BPRTBV.KEY.TLR = BPCFBVTL.TLR;
        IBS.init(SCCGWA, BPCRTBV);
        BPCRTBV.INFO.FUNC = 'Q';
        S000_CALL_BPZRTBV();
        if (BPCRTBV.RETURN_INFO == 'F') {
            if (BPRTBV.BV_BOX_CHK == 'N') {
                BPCFBVTL.BV_BCHK_FLG = 'N';
            } else {
                BPCFBVTL.BV_BCHK_FLG = 'Y';
            }
            if (BPRTBV.BV_VLT_CHK == 'N') {
                BPCFBVTL.BV_VCHK_FLG = 'N';
            } else {
                BPCFBVTL.BV_VCHK_FLG = 'Y';
            }
            if (BPRTBV.BL_BOX_CHK == 'N') {
                BPCFBVTL.BL_BCHK_FLG = 'N';
            } else {
                BPCFBVTL.BL_BCHK_FLG = 'Y';
            }
            if (BPRTBV.BL_VLT_CHK == 'N') {
                BPCFBVTL.BL_VCHK_FLG = 'N';
            } else {
                BPCFBVTL.BL_VCHK_FLG = 'Y';
            }
        }
    }
    public void B300_CHK_ONWAY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBMOV);
        BPRBMOV.MOV_STS = '0';
        BPRBMOV.OUT_TLR = BPCFBVTL.TLR;
        BPRBMOV.IN_TLR = BPCFBVTL.TLR;
        BPRBMOV.TYPE = '0';
        IBS.init(SCCGWA, BPCRBMOB);
        BPCRBMOB.INFO.FUNC = '2';
        S000_CALL_BPZRBMOB();
        IBS.init(SCCGWA, BPCRBMOB);
        BPCRBMOB.INFO.FUNC = 'N';
        S000_CALL_BPZRBMOB();
        if (BPCRBMOB.RETURN_INFO == 'N') {
            BPCFBVTL.BV_ONWAY_FLG = 'N';
        } else {
            BPCFBVTL.BV_ONWAY_FLG = 'Y';
        }
        IBS.init(SCCGWA, BPCRBMOB);
        BPCRBMOB.INFO.FUNC = 'E';
        S000_CALL_BPZRBMOB();
        IBS.init(SCCGWA, BPRBMOV);
        BPRBMOV.MOV_STS = '0';
        BPRBMOV.OUT_TLR = BPCFBVTL.TLR;
        BPRBMOV.IN_TLR = BPCFBVTL.TLR;
        BPRBMOV.TYPE = '1';
        IBS.init(SCCGWA, BPCRBMOB);
        BPCRBMOB.INFO.FUNC = '2';
        S000_CALL_BPZRBMOB();
        IBS.init(SCCGWA, BPCRBMOB);
        BPCRBMOB.INFO.FUNC = 'N';
        S000_CALL_BPZRBMOB();
        if (BPCRBMOB.RETURN_INFO == 'N') {
            BPCFBVTL.BL_ONWAY_FLG = 'N';
        } else {
            BPCFBVTL.BL_ONWAY_FLG = 'Y';
        }
        IBS.init(SCCGWA, BPCRBMOB);
        BPCRBMOB.INFO.FUNC = 'E';
        S000_CALL_BPZRBMOB();
    }
    public void S000_CALL_BPZRTBDB() throws IOException,SQLException,Exception {
        BPCRTBDB.INFO.POINTER = BPRTBVD;
        BPCRTBDB.INFO.LEN = 160;
        IBS.CALLCPN(SCCGWA, CPN_R_BRW_TBVD, BPCRTBDB);
    }
    public void S000_CALL_BPZRTBV() throws IOException,SQLException,Exception {
        BPCRTBV.INFO.POINTER = BPRTBV;
        BPCRTBV.INFO.LEN = 30;
        IBS.CALLCPN(SCCGWA, CPN_R_MGM_TBV, BPCRTBV);
    }
    public void S000_CALL_BPZRBMOB() throws IOException,SQLException,Exception {
        BPCRBMOB.INFO.POINTER = BPRBMOV;
        BPCRBMOB.INFO.LEN = 900;
        IBS.CALLCPN(SCCGWA, CPN_R_BRW_BMOV, BPCRBMOB);
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_TLR_INF_QUERY, BPCFTLRQ);
    }
    public void S000_CALL_BPZRVHPB() throws IOException,SQLException,Exception {
        BPCRVHPB.INFO.POINTER = BPRVHPB;
        BPCRVHPB.INFO.LEN = 152;
        IBS.CALLCPN(SCCGWA, CPN_R_BPTVHPB_MTN, BPCRVHPB);
        if (BPCRVHPB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRVHPB.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCFBVTL.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCFBVTL = ");
            CEP.TRC(SCCGWA, BPCFBVTL);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
