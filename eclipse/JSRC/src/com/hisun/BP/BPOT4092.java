package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4092 {
    String K_CPN_S_MAINTAIN_TWND = "BP-S-MAINTAIN-TWND  ";
    String CPN_SCSSCKDT = "SCSSCKDT";
    String CPN_BP_I_INQ_CITY = "BP-I-INQ-CITY     ";
    String K_FMT_CD_1 = "BPX01";
    String K_FMT_CD_2 = "BP747";
    String K_PUBLIC_TYPE = "EVENT";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_CNT = 0;
    int WS_NUM = 0;
    char WS_AMT_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSPBL BPCSPBL = new BPCSPBL();
    BPCPQAMO BPCPQAMO = new BPCPQAMO();
    BPCQBKPM BPCQBKPM = new BPCQBKPM();
    BPCPITM BPCPITM = new BPCPITM();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB4090_AWA_4090 BPB4090_AWA_4090;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4092 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4090_AWA_4090>");
        BPB4090_AWA_4090 = (BPB4090_AWA_4090) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        IBS.init(SCCGWA, BPCSPBL);
        BPCSPBL.FUNC = 'U';
        BPCSPBL.EFF_DT = BPB4090_AWA_4090.EFF_DT;
        BPCSPBL.EXP_DT = BPB4090_AWA_4090.EXP_DT;
        BPCSPBL.KEY.MOD_NO = BPB4090_AWA_4090.MOD_NO;
        BPCSPBL.KEY.BOOK_FLG = BPB4090_AWA_4090.BOOK_FLG;
        BPCSPBL.CNT = BPB4090_AWA_4090.CNT;
        BPCSPBL.MOD_NAME = BPCPQAMO.DATA_INFO.MOD_NAME;
        BPCSPBL.DATA.ITM_PNT = BPB4090_AWA_4090.ITM_PNT;
        BPCSPBL.DATA.ITM_NO = BPB4090_AWA_4090.ITM_NO;
        BPCSPBL.DATA.ITM_SEQ = BPB4090_AWA_4090.SEQ;
        BPCSPBL.DATA.REMARK = BPB4090_AWA_4090.REMARK;
        WS_AMT_FLG = 'N';
        for (WS_CNT = 1; WS_CNT <= 20; WS_CNT += 1) {
            if (BPB4090_AWA_4090.DATA_FLG[WS_CNT-1].AMT_FLG != ' ' 
                && BPB4090_AWA_4090.DATA_FLG[WS_CNT-1].AMT_NUM == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AMT_PNT_FLG_INPUT;
                S000_ERR_MSG_PROC();
            }
            if (BPB4090_AWA_4090.DATA_FLG[WS_CNT-1].AMT_NUM != 0) {
                WS_NUM = BPB4090_AWA_4090.DATA_FLG[WS_CNT-1].AMT_NUM;
                CEP.TRC(SCCGWA, BPCSPBL.DATA.AMT_PNT[WS_NUM-1].AMT_FLG);
                if (BPCSPBL.DATA.AMT_PNT[WS_NUM-1].AMT_FLG != 0X00 
                    && BPCSPBL.DATA.AMT_PNT[WS_NUM-1].AMT_FLG != ' ') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AMT_PNT_BE_SAME;
                    S000_ERR_MSG_PROC();
                }
                BPCSPBL.DATA.AMT_PNT[WS_NUM-1].AMT_FLG = BPB4090_AWA_4090.DATA_FLG[WS_CNT-1].AMT_FLG;
            }
            if (BPB4090_AWA_4090.DATA_FLG[WS_CNT-1].AMT_NUM != 0) {
                WS_AMT_FLG = 'Y';
            }
        }
        if (WS_AMT_FLG == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_9389;
            S000_ERR_MSG_PROC();
        }
        S010_CALL_BPZSPBL();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB4090_AWA_4090.MOD_NO.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ACCU_MOD_MUST_INPUT;
            WS_FLD_NO = BPB4090_AWA_4090.MOD_NO_NO;
            S000_ERR_MSG_PROC();
        } else {
            IBS.init(SCCGWA, BPCPQAMO);
            BPCPQAMO.DATA_INFO.MOD_NO = BPB4090_AWA_4090.MOD_NO;
            BPCPQAMO.FUNC = 'Q';
            S030_CALL_BPZPQAMO();
            CEP.TRC(SCCGWA, BPCPQAMO.RC);
            if (BPCPQAMO.RC.RC_CODE != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ACCU_MOD_NOT_EXIST;
                WS_FLD_NO = BPB4090_AWA_4090.MOD_NO_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB4090_AWA_4090.BOOK_FLG.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_GL_BOOK_MUST_INPUT;
            WS_FLD_NO = BPB4090_AWA_4090.BOOK_FLG_NO;
            S000_ERR_MSG_PROC();
        } else {
            IBS.init(SCCGWA, BPCQBKPM);
            BPCQBKPM.KEY.BK_FLG = BPB4090_AWA_4090.BOOK_FLG;
            BPCQBKPM.FUNC = 'Q';
            S020_CALL_BPZQBKPM();
            CEP.TRC(SCCGWA, BPCQBKPM.RC);
            if (BPCQBKPM.RC.RC_RTNCODE != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQBKPM.RC);
                WS_FLD_NO = BPB4090_AWA_4090.BOOK_FLG_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB4090_AWA_4090.ITM_NO.trim().length() > 0) {
            IBS.init(SCCGWA, BPCPITM);
            BPCPITM.INPUT_DATA.NO = BPB4090_AWA_4090.ITM_NO;
            BPCPITM.INPUT_DATA.COA_FLG = BPCQBKPM.DATA[1-1].COA_FLG;
            S040_CALL_BPZPITM();
            if (BPCPITM.RC.RTNCODE != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPITM.RC);
                WS_FLD_NO = BPB4090_AWA_4090.ITM_NO_NO;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S010_CALL_BPZSPBL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MAINT-PBL", BPCSPBL);
    }
    public void S020_CALL_BPZQBKPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-Q-MAINT-AMBKP", BPCQBKPM);
    }
    public void S030_CALL_BPZPQAMO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-AMOD", BPCPQAMO);
    }
    public void S040_CALL_BPZPITM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-CHK-ITM", BPCPITM);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
