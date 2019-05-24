package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2128 {
    DBParm BPTBRIS_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BP134";
    String CPN_S_CSHAPP_MAINTAIN = "BP-S-CSHAPP-MAINTAIN";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    char K_RUN_MODE = 'O';
    short K_NUM = 1;
    String K_BPFBAS_SEQ = "CASHMOVE";
    String K_SEQ_TYPE = "CMOVE";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_CNT = 0;
    double WS_AMT = 0;
    char WS_FLG = ' ';
    char WS_DATA_OVERFLOW_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCOLIBB BPCOLIBB = new BPCOLIBB();
    BPCPQBNK BPCPQBNK = new BPCPQBNK();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    BPCPQORR BPCPQORR = new BPCPQORR();
    BPRBRIS BPRBRIS = new BPRBRIS();
    SCCGWA SCCGWA;
    BPB2128_AWA_2128 BPB2128_AWA_2128;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT2128 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2128_AWA_2128>");
        BPB2128_AWA_2128 = (BPB2128_AWA_2128) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B010_CHECK_INPUT();
            if (pgmRtn) return;
            B020_UPD_CASH_APP();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR35);
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        S000_ERR_MSG_PROC_LAST();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2128_AWA_2128.SEQ);
        CEP.TRC(SCCGWA, BPB2128_AWA_2128.APP_NO);
        CEP.TRC(SCCGWA, BPB2128_AWA_2128.APP_BR);
        CEP.TRC(SCCGWA, BPB2128_AWA_2128.UP_BR);
        CEP.TRC(SCCGWA, BPB2128_AWA_2128.UP_TLR);
        CEP.TRC(SCCGWA, BPB2128_AWA_2128.ALLOT_TP);
        CEP.TRC(SCCGWA, BPB2128_AWA_2128.APP_TYPE);
        CEP.TRC(SCCGWA, BPB2128_AWA_2128.CCY);
        CEP.TRC(SCCGWA, BPB2128_AWA_2128.OUT_AMT);
        CEP.TRC(SCCGWA, BPB2128_AWA_2128.PVAL_INF[1-1].PVAL);
        CEP.TRC(SCCGWA, BPB2128_AWA_2128.PVAL_INF[1-1].NUM);
        if (BPB2128_AWA_2128.APP_NO != 0) {
            WS_FLG = '1';
        } else {
            WS_FLG = '2';
        }
        if (BPB2128_AWA_2128.SEQ == 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR61);
        }
        if (BPB2128_AWA_2128.UP_TLR.trim().length() == 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR132);
        }
        if (BPB2128_AWA_2128.CCY.trim().length() == 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR57);
        }
        if (BPB2128_AWA_2128.OUT_AMT == 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR58);
        }
        if (WS_FLG == '1') {
            if (BPB2128_AWA_2128.APP_NO == 0) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR133);
            }
        }
        if (WS_FLG == '2') {
            if (BPB2128_AWA_2128.APP_BR == ' ') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR134);
            }
            if (BPB2128_AWA_2128.UP_BR == ' ') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR135);
            }
            if (BPB2128_AWA_2128.ALLOT_TP == ' ') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR136);
            }
            IBS.init(SCCGWA, BPRBRIS);
            BPRBRIS.KEY.BR = BPB2128_AWA_2128.UP_BR;
            BPRBRIS.KEY.LMT_CCY = BPB2128_AWA_2128.CCY;
            T000_READ_BPTBRIS();
            if (pgmRtn) return;
            if (BPB2128_AWA_2128.ALLOT_TP != '1' 
                && BPB2128_AWA_2128.ALLOT_TP != '2' 
                && BPB2128_AWA_2128.ALLOT_TP != '3') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR139);
            }
            CEP.TRC(SCCGWA, BPB2128_AWA_2128.ALLOT_TP);
            IBS.init(SCCGWA, BPCPQORR);
            if (BPB2128_AWA_2128.ALLOT_TP == '1') {
                BPCPQORR.TYP = "07";
            }
            if (BPB2128_AWA_2128.ALLOT_TP == '2') {
                BPCPQORR.TYP = "08";
            }
            if (BPB2128_AWA_2128.ALLOT_TP == '3') {
                BPCPQORR.TYP = "11";
            }
            BPCPQORR.BR = BPB2128_AWA_2128.APP_BR;
            CEP.TRC(SCCGWA, BPCPQORR.BR);
            CEP.TRC(SCCGWA, BPCPQORR.TYP);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_BANK);
            S000_CALL_BPZPQORR();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPQORR.REL_BR);
            if (BPCPQORR.REL_BR != BPB2128_AWA_2128.UP_BR 
                || BPCPQORR.RC.RC_CODE != 0) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR137);
            }
            IBS.init(SCCGWA, BPCFTLRQ);
            BPCFTLRQ.INFO.TLR = BPB2128_AWA_2128.UP_TLR;
            S000_CALL_BPZFTLRQ();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCFTLRQ.INFO.NEW_BR);
            if (BPCFTLRQ.INFO.NEW_BR != BPB2128_AWA_2128.UP_BR) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR138);
            }
        }
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = BPB2128_AWA_2128.CCY;
        S000_CALL_BPZQCCY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCQCCY.DATA.CASH_MTH);
        WS_AMT = 0;
        for (WS_CNT = 1; WS_CNT <= 20; WS_CNT += 1) {
            if (BPB2128_AWA_2128.PVAL_INF[WS_CNT-1].PVAL != 0) {
                if (BPB2128_AWA_2128.PVAL_INF[WS_CNT-1].PVAL != 88 
                    && BPB2128_AWA_2128.PVAL_INF[WS_CNT-1].PVAL != 99) {
                    WS_AMT = WS_AMT + BPB2128_AWA_2128.PVAL_INF[WS_CNT-1].PVAL * BPB2128_AWA_2128.PVAL_INF[WS_CNT-1].NUM;
                } else {
                    if (BPCQCCY.DATA.CASH_MTH == 0) {
                        WS_AMT = WS_AMT + 1 * BPB2128_AWA_2128.PVAL_INF[WS_CNT-1].NUM;
                    } else if (BPCQCCY.DATA.CASH_MTH == 1) {
                        WS_AMT = WS_AMT + 0.1 * BPB2128_AWA_2128.PVAL_INF[WS_CNT-1].NUM;
                    } else if (BPCQCCY.DATA.CASH_MTH == 2) {
                        WS_AMT = WS_AMT + 0.01 * BPB2128_AWA_2128.PVAL_INF[WS_CNT-1].NUM;
                    } else if (BPCQCCY.DATA.CASH_MTH == 3) {
                        WS_AMT = WS_AMT + 0.001 * BPB2128_AWA_2128.PVAL_INF[WS_CNT-1].NUM;
                    } else {
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR59);
                    }
                }
            }
        }
        CEP.TRC(SCCGWA, WS_AMT);
        if (WS_AMT != BPB2128_AWA_2128.OUT_AMT) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR60);
        }
        if (BPB2128_AWA_2128.APP_NO == 0) {
            IBS.init(SCCGWA, BPCSGSEQ);
            BPCSGSEQ.TYPE = K_SEQ_TYPE;
            BPCSGSEQ.CODE = K_BPFBAS_SEQ;
            BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPCSGSEQ.RUN_MODE = K_RUN_MODE;
            BPCSGSEQ.NUM = K_NUM;
            S000_CALL_BPZSGSEQ();
            if (pgmRtn) return;
            BPB2128_AWA_2128.APP_NO = (int) BPCSGSEQ.SEQ;
            CEP.TRC(SCCGWA, BPCSGSEQ.SEQ);
        }
    }
    public void T000_READ_BPTBRIS() throws IOException,SQLException,Exception {
        BPTBRIS_RD = new DBParm();
        BPTBRIS_RD.TableName = "BPTBRIS";
        BPTBRIS_RD.where = "BR = :BPRBRIS.KEY.BR "
            + "AND LMT_CCY = :BPRBRIS.KEY.LMT_CCY";
        BPTBRIS_RD.upd = true;
        BPTBRIS_RD.errhdl = true;
        IBS.READ(SCCGWA, BPRBRIS, this, BPTBRIS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR124);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTBRIS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQORR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG-REL", BPCPQORR);
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-TLR-INF-QUERY", BPCFTLRQ);
        CEP.TRC(SCCGWA, BPCFTLRQ.RC);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR141);
        }
    }
    public void S000_CALL_BPZSGSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-GET-SEQ", BPCSGSEQ);
        if (BPCSGSEQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSGSEQ.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPQBNK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-BANK", BPCPQBNK);
        if (BPCPQBNK.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQBNK.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void B020_UPD_CASH_APP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOLIBB);
        BPCOLIBB.FUNC = 'M';
        BPCOLIBB.OUTPUT_FMT = K_OUTPUT_FMT;
        BPCOLIBB.MODIFY_STS = 'Q';
        BPCOLIBB.FLG = WS_FLG;
        BPCOLIBB.SEQ = BPB2128_AWA_2128.SEQ;
        BPCOLIBB.APP_NO = BPB2128_AWA_2128.APP_NO;
        BPCOLIBB.APP_BR = BPB2128_AWA_2128.APP_BR;
        BPCOLIBB.UP_BR = BPB2128_AWA_2128.UP_BR;
        BPCOLIBB.UP_TLR = BPB2128_AWA_2128.UP_TLR;
        BPCOLIBB.ALLOT_TP = BPB2128_AWA_2128.ALLOT_TP;
        BPCOLIBB.APP_TYPE = BPB2128_AWA_2128.APP_TYPE;
        BPCOLIBB.CCY = BPB2128_AWA_2128.CCY;
        BPCOLIBB.OUT_AMT = BPB2128_AWA_2128.OUT_AMT;
        for (WS_CNT = 1; WS_CNT <= 20; WS_CNT += 1) {
            BPCOLIBB.PVAL_INFO[WS_CNT-1].PVAL = BPB2128_AWA_2128.PVAL_INF[WS_CNT-1].PVAL;
            BPCOLIBB.PVAL_INFO[WS_CNT-1].NUM = BPB2128_AWA_2128.PVAL_INF[WS_CNT-1].NUM;
        }
        S000_CALL_BPZSLIBB();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZSLIBB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_CSHAPP_MAINTAIN, BPCOLIBB);
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
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
