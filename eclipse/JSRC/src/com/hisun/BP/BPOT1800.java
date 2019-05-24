package com.hisun.BP;

import com.hisun.CI.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1800 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String CPN_P_INQ_ORG_STATION = "BP-P-INQ-ORG-STATION";
    String CPN_INQ_ACR_INF = "CI-INQ-ACR-INF";
    String CPN_P_QUERY_OCAC_INFO = "BP-P-QUERY-OCAC-INFO";
    String K_OUTPUT_FMT = "SCZ01";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPOT1800_WS_DATA WS_DATA = new BPOT1800_WS_DATA();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    CICQACRI CICQACRI = new CICQACRI();
    BPCPOCAC BPCPOCAC = new BPCPOCAC();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCPRGST BPCPRGST = new BPCPRGST();
    CICOACRB CICOACRB = new CICOACRB();
    BPCPORUP BPCPORUP = new BPCPORUP();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB1801_AWA_1801 BPB1801_AWA_1801;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT1800 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1801_AWA_1801>");
        BPB1801_AWA_1801 = (BPB1801_AWA_1801) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB1801_AWA_1801.BR);
        CEP.TRC(SCCGWA, BPB1801_AWA_1801.OPEN_TLR);
        if (BPB1801_AWA_1801.BR != 0) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = BPB1801_AWA_1801.BR;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_BR_NOTFND);
            }
        }
        if (BPB1801_AWA_1801.OPEN_DT != 0 
            && BPB1801_AWA_1801.CLO_DT != 0) {
            if (BPB1801_AWA_1801.OPEN_DT > BPB1801_AWA_1801.CLO_DT) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_OPENDT_GT_CLODT);
            }
        }
        if (BPB1801_AWA_1801.BR != 0 
            && (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != BPB1801_AWA_1801.BR)) {
            IBS.init(SCCGWA, BPCPQORG);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
            BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPQORG.TYP);
            CEP.TRC(SCCGWA, BPCPQORG.SUPR_BR);
            CEP.TRC(SCCGWA, BPCPQORG.LVL);
            if (BPCPQORG.TYP.equalsIgnoreCase("01") 
                || BPCPQORG.TYP.equalsIgnoreCase("02") 
                || BPCPQORG.TYP.equalsIgnoreCase("03")) {
            } else {
                IBS.init(SCCGWA, BPCPORUP);
                BPCPORUP.DATA_INFO.BR = BPB1801_AWA_1801.BR;
                S000_CALL_BPZPORUP();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.SUPR_GRP[1-1].SUPR_BR);
                CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.SUPR_GRP[2-1].SUPR_BR);
                CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.SUPR_GRP[3-1].SUPR_BR);
                if (BPCPQORG.SUPR_BR == BPCPORUP.DATA_INFO.SUPR_GRP[1-1].SUPR_BR 
                    || BPCPQORG.SUPR_BR == BPCPORUP.DATA_INFO.SUPR_GRP[2-1].SUPR_BR 
                    || BPCPQORG.SUPR_BR == BPCPORUP.DATA_INFO.SUPR_GRP[3-1].SUPR_BR) {
                    CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.LVL);
                    if (BPCPQORG.LVL > BPCPORUP.DATA_INFO.LVL) {
                    } else {
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_NO_AUTO_INQUERY);
                    }
                } else {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_NO_AUTO_INQUERY);
                }
            }
        }
        if (BPB1801_AWA_1801.OPEN_TLR.trim().length() > 0) {
            IBS.init(SCCGWA, BPCPQORG);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
            BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPQORG.TYP);
            if (BPCPQORG.TYP.equalsIgnoreCase("01") 
                || BPCPQORG.TYP.equalsIgnoreCase("02") 
                || BPCPQORG.TYP.equalsIgnoreCase("03")) {
            } else {
                IBS.init(SCCGWA, BPCFTLRQ);
                BPCFTLRQ.INFO.TLR = BPB1801_AWA_1801.OPEN_TLR;
                S000_CALL_BPZFTLRQ();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCFTLRQ.INFO.TLR_BR);
                IBS.init(SCCGWA, BPCPORUP);
                BPCPORUP.DATA_INFO.BR = BPCFTLRQ.INFO.TLR_BR;
                S000_CALL_BPZPORUP();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.SUPR_GRP[1-1].SUPR_BR);
                CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.SUPR_GRP[2-1].SUPR_BR);
                CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.SUPR_GRP[3-1].SUPR_BR);
                if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH == BPCPORUP.DATA_INFO.SUPR_GRP[1-1].SUPR_BR 
                    || SCCGWA.COMM_AREA.BR_DP.TR_BRANCH == BPCPORUP.DATA_INFO.SUPR_GRP[2-1].SUPR_BR 
                    || SCCGWA.COMM_AREA.BR_DP.TR_BRANCH == BPCPORUP.DATA_INFO.SUPR_GRP[3-1].SUPR_BR 
                    || SCCGWA.COMM_AREA.BR_DP.TR_BRANCH == BPCFTLRQ.INFO.TLR_BR) {
                } else {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_NO_AUTO_INQUERY);
                }
            }
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOCAC);
        CEP.TRC(SCCGWA, BPB1801_AWA_1801.OPEN_TLR);
        CEP.TRC(SCCGWA, BPB1801_AWA_1801.BR);
        CEP.TRC(SCCGWA, BPB1801_AWA_1801.OPEN_DT);
        CEP.TRC(SCCGWA, BPB1801_AWA_1801.CLO_DT);
        CEP.TRC(SCCGWA, BPB1801_AWA_1801.STS);
        CEP.TRC(SCCGWA, BPB1801_AWA_1801.WORK_TYP);
        CEP.TRC(SCCGWA, BPB1801_AWA_1801.AC);
        CEP.TRC(SCCGWA, BPB1801_AWA_1801.BV_TYP);
        CEP.TRC(SCCGWA, BPB1801_AWA_1801.BV_NO);
        CEP.TRC(SCCGWA, BPB1801_AWA_1801.ID_TYP);
        CEP.TRC(SCCGWA, BPB1801_AWA_1801.ID_NO);
        CEP.TRC(SCCGWA, BPB1801_AWA_1801.SEQ);
        CEP.TRC(SCCGWA, BPB1801_AWA_1801.CCY);
        CEP.TRC(SCCGWA, BPB1801_AWA_1801.CCY_TYPE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        CEP.TRC(SCCGWA, BPB1801_AWA_1801.COL_IND);
        BPCPOCAC.OUTPUT_FMT = K_OUTPUT_FMT;
        BPCPOCAC.DATA_INFO.OPEN_TLR = BPB1801_AWA_1801.OPEN_TLR;
        BPCPOCAC.DATA_INFO.BR = BPB1801_AWA_1801.BR;
        BPCPOCAC.DATA_INFO.OPEN_DT = BPB1801_AWA_1801.OPEN_DT;
        BPCPOCAC.DATA_INFO.CLO_DT = BPB1801_AWA_1801.CLO_DT;
        BPCPOCAC.DATA_INFO.STS = BPB1801_AWA_1801.STS;
        BPCPOCAC.DATA_INFO.WORK_TYP = BPB1801_AWA_1801.WORK_TYP;
        BPCPOCAC.DATA_INFO.AC = BPB1801_AWA_1801.AC;
        BPCPOCAC.DATA_INFO.BV_TYP = BPB1801_AWA_1801.BV_TYP;
        BPCPOCAC.DATA_INFO.BV_NO = BPB1801_AWA_1801.BV_NO;
        BPCPOCAC.DATA_INFO.ID_TYP = BPB1801_AWA_1801.ID_TYP;
        BPCPOCAC.DATA_INFO.ID_NO = BPB1801_AWA_1801.ID_NO;
        BPCPOCAC.DATA_INFO.CI_CNM = BPB1801_AWA_1801.CI_CNM;
        BPCPOCAC.DATA_INFO.SEQ = BPB1801_AWA_1801.SEQ;
        BPCPOCAC.DATA_INFO.CCY = BPB1801_AWA_1801.CCY;
        BPCPOCAC.DATA_INFO.CCY_TYPE = BPB1801_AWA_1801.CCY_TYPE;
        BPCPOCAC.DATA_INFO.COL_IND = BPB1801_AWA_1801.COL_IND;
        BPCPOCAC.INFO.FUNC = 'B';
        if (BPB1801_AWA_1801.AC.trim().length() > 0) {
            BPCPOCAC.INFO.INDEX_FLG = "1";
        } else if (BPB1801_AWA_1801.BV_TYP != ' ') {
            BPCPOCAC.INFO.INDEX_FLG = "4";
        } else if (BPB1801_AWA_1801.ID_TYP.trim().length() > 0 
                && BPB1801_AWA_1801.ID_NO.trim().length() > 0) {
            if (BPB1801_AWA_1801.COL_IND != ' ') {
                BPCPOCAC.INFO.INDEX_FLG = "7";
            } else {
                BPCPOCAC.INFO.INDEX_FLG = "5";
            }
        } else if (BPB1801_AWA_1801.BR != 0 
                && BPB1801_AWA_1801.OPEN_DT != 0 
                && BPB1801_AWA_1801.CLO_DT != 0) {
            BPCPOCAC.INFO.INDEX_FLG = "2";
        } else if (BPB1801_AWA_1801.OPEN_TLR.trim().length() > 0 
                && BPB1801_AWA_1801.OPEN_DT != 0 
                && BPB1801_AWA_1801.CLO_DT != 0) {
            BPCFTLRQ.INFO.TLR = BPB1801_AWA_1801.OPEN_TLR;
            S000_CALL_BPZFTLRQ();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCFTLRQ.INFO.TLR_BR);
            BPCPOCAC.DATA_INFO.BR = BPCFTLRQ.INFO.TLR_BR;
            BPCPOCAC.INFO.INDEX_FLG = "2";
        } else if (BPB1801_AWA_1801.CI_CNM.trim().length() > 0 
                && BPB1801_AWA_1801.COL_IND != ' ') {
            BPCPOCAC.INFO.INDEX_FLG = "8";
        } else {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_NO_INQUERY);
        }
        S000_CALL_BPZPOCAC();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_ORG_NOTFND);
        }
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_TLR_INF_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0 
            && BPCFTLRQ.RC.RC_CODE == BPCMSG_ERROR_MSG.BP_TLR_NOTFND) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_NOTFND);
        }
    }
    public void S000_CALL_BPZPRGST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG_STATION, BPCPRGST);
        if (BPCPRGST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRGST.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_INQ_ACR_INF, CICQACRI);
        if (CICQACRI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACRI.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPOCAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_QUERY_OCAC_INFO, BPCPOCAC);
    }
    public void S000_CALL_BPZPORUP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-SUPR-ORG", BPCPORUP);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
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
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
