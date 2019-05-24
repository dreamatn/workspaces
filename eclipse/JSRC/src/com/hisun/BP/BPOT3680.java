package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT3680 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BP189";
    String CPN_S_SC_TLSCQUR = "BP-S-SC-TO-TLSCQUR";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG      ";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String CPN_R_BPTVHPB_MTN = "BP-R-BPTVHPB-MTN  ";
    String CPN_P_INQ_ORG_STATION = "BP-P-INQ-ORG-STATION";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_TEMP_PLBOX_NO = " ";
    int WS_CNT = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCOTLSC BPCOTSCI = new BPCOTLSC();
    BPRVHPB BPRVHPB = new BPRVHPB();
    BPCRVHPB BPCRVHPB = new BPCRVHPB();
    BPCSTLSC BPCSTLSC = new BPCSTLSC();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCPRGST BPCPRGST = new BPCPRGST();
    BPCSTSCI BPCSTSCI = new BPCSTSCI();
    SCCGWA SCCGWA;
    BPB3600_AWA_3600 BPB3600_AWA_3600;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT3680 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB3600_AWA_3600>");
        BPB3600_AWA_3600 = (BPB3600_AWA_3600) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSTLSC);
        IBS.init(SCCGWA, BPCSTSCI);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (BPB3600_AWA_3600.TLR.trim().length() == 0) {
            IBS.init(SCCGWA, BPRVHPB);
            IBS.init(SCCGWA, BPCRVHPB);
            BPRVHPB.BR = BPB3600_AWA_3600.BR;
            BPRVHPB.POLL_BOX_IND = BPB3600_AWA_3600.PL_FLG;
            BPCRVHPB.INFO.FUNC = 'I';
            S000_CALL_BPZRVHPB();
            if (pgmRtn) return;
            BPCRVHPB.INFO.FUNC = 'N';
            S000_CALL_BPZRVHPB();
            if (pgmRtn) return;
            for (WS_CNT = 1; BPCRVHPB.RETURN_INFO != 'N'; WS_CNT += 1) {
                CEP.TRC(SCCGWA, BPRVHPB.KEY.POOL_BOX_NO);
                CEP.TRC(SCCGWA, "TESTSUCCESS");
                WS_TEMP_PLBOX_NO = BPRVHPB.KEY.POOL_BOX_NO;
                if (WS_CNT == 1) {
                    B300_OUT_TITLE();
                    if (pgmRtn) return;
                }
                B020_QUE_RECORD();
                if (pgmRtn) return;
                BPCRVHPB.INFO.FUNC = 'N';
                S000_CALL_BPZRVHPB();
                if (pgmRtn) return;
            }
            BPCRVHPB.INFO.FUNC = 'E';
            S000_CALL_BPZRVHPB();
            if (pgmRtn) return;
        } else {
            B300_OUT_TITLE();
            if (pgmRtn) return;
            B020_QUE_RECORD();
            if (pgmRtn) return;
        }
    }
    public void B300_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 318;
        SCCMPAG.SCR_ROW_CNT = 20;
        SCCMPAG.SCR_COL_CNT = 6;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB3600_AWA_3600.SC_TYPE);
        CEP.TRC(SCCGWA, BPB3600_AWA_3600.CODE_NO);
        CEP.TRC(SCCGWA, BPB3600_AWA_3600.MC_NO);
        CEP.TRC(SCCGWA, BPB3600_AWA_3600.BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, BPB3600_AWA_3600.TLR);
        if (BPB3600_AWA_3600.BR != 0) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = BPB3600_AWA_3600.BR;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_NOTFND;
                WS_FLD_NO = BPB3600_AWA_3600.BR_NO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPB3600_AWA_3600.TLR.trim().length() > 0) {
            IBS.init(SCCGWA, BPCFTLRQ);
            BPCFTLRQ.INFO.TLR = BPB3600_AWA_3600.TLR;
            S000_CALL_BPZFTLRQ();
            if (pgmRtn) return;
            if (BPCFTLRQ.RC.RC_CODE != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_NOTFND;
                WS_FLD_NO = BPB3600_AWA_3600.TLR_NO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPCFTLRQ.INFO.TX_LVL == '0') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_DIRECTOR_TLR;
                WS_FLD_NO = 0;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, BPRVHPB);
            IBS.init(SCCGWA, BPCRVHPB);
            BPRVHPB.BR = BPB3600_AWA_3600.BR;
            BPRVHPB.CUR_TLR = BPB3600_AWA_3600.TLR;
            BPRVHPB.POLL_BOX_IND = BPB3600_AWA_3600.PL_FLG;
            BPRVHPB.RELATE_FLG = 'Y';
            BPRVHPB.STS = 'N';
            BPCRVHPB.INFO.FUNC = 'F';
            S000_CALL_BPZRVHPB();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPRVHPB.POLL_BOX_IND);
            CEP.TRC(SCCGWA, BPRVHPB.KEY.POOL_BOX_NO);
            if (BPCRVHPB.RETURN_INFO == 'F') {
                WS_TEMP_PLBOX_NO = BPRVHPB.KEY.POOL_BOX_NO;
                CEP.TRC(SCCGWA, WS_TEMP_PLBOX_NO);
            } else {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_NOT_BVPB;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_QUE_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSTSCI);
        R000_TRANS_DATA_PARAMETER();
        if (pgmRtn) return;
        S000_CALL_BPZSTSCI();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        if (BPB3600_AWA_3600.BR == 0) {
            BPCSTSCI.BR = 0;
        } else {
            BPCSTSCI.BR = BPB3600_AWA_3600.BR;
        }
        if (WS_TEMP_PLBOX_NO.trim().length() == 0) {
            BPCSTSCI.POOL_BOX_NO = "%%%%%%";
        } else {
            BPCSTSCI.POOL_BOX_NO = WS_TEMP_PLBOX_NO;
        }
        CEP.TRC(SCCGWA, BPCSTSCI.POOL_BOX_NO);
        if (BPB3600_AWA_3600.CODE_NO.trim().length() == 0) {
            BPCSTSCI.CODE_NO = "%%%%%%%%%%%%%%%%%%%%";
        } else {
            BPCSTSCI.CODE_NO = BPB3600_AWA_3600.CODE_NO;
        }
        if (BPB3600_AWA_3600.SC_TYPE == ' ') {
            BPCSTSCI.SC_TYPE = ALL.charAt(0);
        } else {
            BPCSTSCI.SC_TYPE = BPB3600_AWA_3600.SC_TYPE;
        }
        if (BPB3600_AWA_3600.SC_STS == ' ') {
            BPCSTSCI.SC_STS = ALL.charAt(0);
        } else {
            BPCSTSCI.SC_STS = BPB3600_AWA_3600.SC_STS;
        }
    }
    public void S000_CALL_BPZSTSCI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_SC_TLSCQUR, BPCSTSCI);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRVHPB() throws IOException,SQLException,Exception {
        BPCRVHPB.INFO.POINTER = BPRVHPB;
        BPCRVHPB.INFO.LEN = 152;
        IBS.CALLCPN(SCCGWA, CPN_R_BPTVHPB_MTN, BPCRVHPB);
        if (BPCRVHPB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRVHPB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRGST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_P_INQ_ORG_STATION;
        SCCCALL.COMMAREA_PTR = BPCPRGST;
        SCCCALL.ERR_FLDNO = BPB3600_AWA_3600.TLR_NO;
        IBS.CALL(SCCGWA, SCCCALL);
        if (BPCPRGST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRGST.RC);
            WS_FLD_NO = BPB3600_AWA_3600.BR_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_TLR_INF_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            WS_FLD_NO = 0;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
