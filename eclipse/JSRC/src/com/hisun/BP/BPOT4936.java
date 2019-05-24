package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4936 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY  ";
    String CPN_F_ACCU_QUERY = "BP-F-ACCU-QUERY     ";
    String CPN_P_INQ_PUB_CD = "BP-P-INQ-PC         ";
    String K_ACCU_TYP = "ACCU ";
    int K_MAX_COL = 99;
    int K_MAX_ROW = 15;
    int K_COL_CNT = 3;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_CNT = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCOTOTQ BPCOTOTQ = new BPCOTOTQ();
    BPCFBTAQ BPCFBTAQ = new BPCFBTAQ();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    SCCGWA SCCGWA;
    BPB4942_AWA_4942 BPB4942_AWA_4942;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT4936 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4942_AWA_4942>");
        BPB4942_AWA_4942 = (BPB4942_AWA_4942) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_TELLER_ACCU_INFO_QUERY();
        if (pgmRtn) return;
        B030_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB4942_AWA_4942.TLR.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_MUST_INPUT;
            WS_FLD_NO = BPB4942_AWA_4942.TLR_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_TELLER_ACCU_INFO_QUERY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFBTAQ);
        BPCFBTAQ.OP_CODE = 'T';
        BPCFBTAQ.TLR = BPB4942_AWA_4942.TLR;
        S000_CALL_BPCFBTAQ();
        if (pgmRtn) return;
    }
    public void B030_OUTPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "111");
        for (WS_CNT = 1; (WS_CNT <= 30) 
            && (BPCFBTAQ.ARRAY[WS_CNT-1].CCY.trim().length() != 0) 
            && SCCMPAG.FUNC != 'E'; WS_CNT += 1) {
            if (WS_CNT == 1) {
                CEP.TRC(SCCGWA, "222");
                B030_01_OUT_TITLE();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, WS_CNT);
            CEP.TRC(SCCGWA, BPCFBTAQ.ARRAY[WS_CNT-1].ACCU_TYP);
            CEP.TRC(SCCGWA, BPCFBTAQ.ARRAY[WS_CNT-1].CCY);
            if (BPCFBTAQ.ARRAY[WS_CNT-1].ACCU_TYP.trim().length() > 0) {
                IBS.init(SCCGWA, BPCOQPCD);
                BPCOQPCD.INPUT_DATA.TYPE = K_ACCU_TYP;
                BPCOQPCD.INPUT_DATA.CODE = BPCFBTAQ.ARRAY[WS_CNT-1].ACCU_TYP;
                S000_CALL_BPZPQPCD();
                if (pgmRtn) return;
            }
            B030_02_OUT_BRW_DATA();
            if (pgmRtn) return;
        }
    }
    public void B030_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B030_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOTOTQ);
        BPCOTOTQ.ACCU_TYP = BPCFBTAQ.ARRAY[WS_CNT-1].ACCU_TYP;
        BPCOTOTQ.ACCU_DESC = BPCOQPCD.OUTPUT_DATA.CODE_INFO.NAME;
        BPCOTOTQ.NORMAL_TX_COUNT = BPCFBTAQ.ARRAY[WS_CNT-1].NORMAL_TX_COUNT;
        BPCOTOTQ.NORMAL_AMT = BPCFBTAQ.ARRAY[WS_CNT-1].NORMAL_AMT;
        BPCOTOTQ.REVERSAL_COUNT = BPCFBTAQ.ARRAY[WS_CNT-1].REVERSAL_COUNT;
        BPCOTOTQ.REVERSAL_AMT = BPCFBTAQ.ARRAY[WS_CNT-1].REVERSAL_AMT;
        BPCOTOTQ.CANCEL_COUNT = BPCFBTAQ.ARRAY[WS_CNT-1].CANCEL_COUNT;
        BPCOTOTQ.CANCEL_AMT = BPCFBTAQ.ARRAY[WS_CNT-1].CANCEL_AMT;
        BPCOTOTQ.CCY = BPCFBTAQ.ARRAY[WS_CNT-1].CCY;
        CEP.TRC(SCCGWA, BPCOTOTQ.ACCU_TYP);
        CEP.TRC(SCCGWA, BPCOTOTQ.ACCU_DESC);
        CEP.TRC(SCCGWA, BPCOTOTQ.NORMAL_TX_COUNT);
        CEP.TRC(SCCGWA, BPCOTOTQ.NORMAL_AMT);
        CEP.TRC(SCCGWA, BPCOTOTQ.REVERSAL_COUNT);
        CEP.TRC(SCCGWA, BPCOTOTQ.REVERSAL_AMT);
        CEP.TRC(SCCGWA, BPCOTOTQ.CANCEL_COUNT);
        CEP.TRC(SCCGWA, BPCOTOTQ.CANCEL_AMT);
        CEP.TRC(SCCGWA, BPCOTOTQ.CCY);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCOTOTQ);
        SCCMPAG.DATA_LEN = 129;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPCFBTAQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_ACCU_QUERY, BPCFBTAQ);
        if (BPCFBTAQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFBTAQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQPCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_PUB_CD, BPCOQPCD);
        if (BPCOQPCD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
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
