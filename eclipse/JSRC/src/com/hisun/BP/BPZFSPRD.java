package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCKDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class BPZFSPRD {
    boolean pgmRtn = false;
    String CPN_U_MAINTAIN_FPRD = "BP-F-U-MAINTAIN-FPRD";
    String CPN_U_MAINTAIN_UBAS = "BP-F-U-MAINTAIN-FBAS";
    String CPN_ITM_INQ = "BP-P-CHK-ACCT-CODE";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String WS_ERR_MSG = " ";
    String WS_TXT = " ";
    String WS_REC = " ";
    int WS_DATE = 0;
    String WS_INP_PRD = " ";
    BPZFSPRD_WS_TS_QUE WS_TS_QUE = new BPZFSPRD_WS_TS_QUE();
    char WS_TBL_BANK_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCOPRDS BPCOPRDS = new BPCOPRDS();
    BPCOPRDB BPCOPRDB = new BPCOPRDB();
    BPCOFBAS BPCOUBAS = new BPCOFBAS();
    BPCOCKAT BPCOCKAT = new BPCOCKAT();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCGWA SCCGWA;
    BPCOFPRD BPCOSPRD;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCOFPRD BPCOSPRD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCOSPRD = BPCOSPRD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFSPRD return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCOSPRD.KEY);
        if (BPCOSPRD.FUNC == 'A' 
            || BPCOSPRD.FUNC == 'U') {
            B010_CHECK_DATA();
            if (pgmRtn) return;
        }
        if (BPCOSPRD.FUNC == 'I') {
            B020_01_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOSPRD.FUNC == 'A') {
            B020_02_CREATE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOSPRD.FUNC == 'U') {
            B020_03_UPDATE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOSPRD.FUNC == 'D') {
            B020_04_DELETE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOSPRD.FUNC == 'B') {
            B020_05_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCOSPRD.FUNC != 'B') {
            B040_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_DATA() throws IOException,SQLException,Exception {
        if (BPCOSPRD.FUNC == 'A') {
            IBS.init(SCCGWA, BPCOUBAS);
            BPCOUBAS.FUNC = 'I';
            BPCOUBAS.KEY.FEE_CODE = BPCOSPRD.KEY.FEE_CD;
            S000_CALL_BPZFUBAS();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCOSPRD.DATE.EFF_DATE);
        if (BPCOSPRD.DATE.EFF_DATE != 0) {
            WS_DATE = BPCOSPRD.DATE.EFF_DATE;
            CEP.TRC(SCCGWA, WS_DATE);
            S000_CALL_SCSSCKDT();
            if (pgmRtn) return;
            if (SCCCKDT.RC != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EFF_DATE_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, BPCOSPRD.DATE.EXP_DATE);
        if (BPCOSPRD.DATE.EXP_DATE != 0) {
            WS_DATE = BPCOSPRD.DATE.EXP_DATE;
            CEP.TRC(SCCGWA, WS_DATE);
            S000_CALL_SCSSCKDT();
            if (pgmRtn) return;
            if (SCCCKDT.RC != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EXP_DATE_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPCOSPRD.DATE.EFF_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EFFDT_LT_ACDT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCOSPRD.DATE.EFF_DATE >= BPCOSPRD.DATE.EXP_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EFFDT_GT_EXPDT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_01_QUERY_PROCESS() throws IOException,SQLException,Exception {
        S000_CALL_BPZFUPRD();
        if (pgmRtn) return;
    }
    public void B020_02_CREATE_PROCESS() throws IOException,SQLException,Exception {
        BPCOSPRD.VAL.FEE_NO = BPCOUBAS.VAL.FEE_NO;
        S000_CALL_BPZFUPRD();
        if (pgmRtn) return;
    }
    public void B020_03_UPDATE_PROCESS() throws IOException,SQLException,Exception {
        S000_CALL_BPZFUPRD();
        if (pgmRtn) return;
    }
    public void B020_04_DELETE_PROCESS() throws IOException,SQLException,Exception {
        S000_CALL_BPZFUPRD();
        if (pgmRtn) return;
    }
    public void B020_05_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        S000_WRITE_MPAG_CNTL();
        if (pgmRtn) return;
        BPCOSPRD.OPT = 'S';
        S000_CALL_BPZFUPRD();
        if (pgmRtn) return;
        WS_INP_PRD = " ";
        WS_INP_PRD = BPCOSPRD.KEY.PROD_CD;
        CEP.TRC(SCCGWA, WS_INP_PRD);
        while (BPCOSPRD.FOUND_FLG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            BPCOSPRD.OPT = 'N';
            S000_CALL_BPZFUPRD();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCOSPRD.KEY.FEE_CD);
            CEP.TRC(SCCGWA, WS_INP_PRD);
            CEP.TRC(SCCGWA, BPCOSPRD.FOUND_FLG);
            if (BPCOSPRD.FOUND_FLG != 'N' 
                && BPCOSPRD.KEY.FEE_CD.trim().length() > 0 
                && (WS_INP_PRD.trim().length() == 0 
                || BPCOSPRD.KEY.PROD_CD.equalsIgnoreCase(WS_INP_PRD))) {
                S000_WRITE_MPAG_ITEM();
                if (pgmRtn) return;
            }
            if ((!BPCOSPRD.KEY.PROD_CD.equalsIgnoreCase(WS_INP_PRD)) 
                && (WS_INP_PRD.trim().length() > 0)) {
                BPCOSPRD.REC_INFO = 'N';
            }
        }
        BPCOSPRD.OPT = 'E';
        S000_CALL_BPZFUPRD();
        if (pgmRtn) return;
    }
    public void B040_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCOSPRD.FUNC);
        if ((BPCOSPRD.FUNC != 'B' 
            && BPCOSPRD.FUNC != 'A')) {
            R000_TRANS_DATA_OUPUT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "CHECK-DATA-OUTPUT-----------------");
            IBS.init(SCCGWA, SCCFMT);
            SCCFMT.FMTID = BPCOSPRD.OUTPUT_FMT;
            CEP.TRC(SCCGWA, SCCFMT.FMTID);
            SCCFMT.DATA_PTR = BPCOPRDS;
            SCCFMT.DATA_LEN = 203;
            IBS.FMT(SCCGWA, SCCFMT);
        }
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
    }
    public void R000_TRANS_DATA_OUPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOUBAS);
        BPCOUBAS.FUNC = 'I';
        BPCOUBAS.KEY.FEE_CODE = BPCOSPRD.KEY.FEE_CD;
        S000_CALL_BPZFUBAS();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCOPRDS);
        BPCOPRDS.FUNC = BPCOSPRD.FUNC;
        CEP.TRC(SCCGWA, "CHECK-DELETE-DATA-OUTPUT");
        CEP.TRC(SCCGWA, BPCOSPRD.FUNC);
