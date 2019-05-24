package com.hisun.BP;

import com.hisun.CI.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1820 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG";
    String CPN_INQ_ACR_INF = "CI-INQ-ACR-INF";
    String CPN_P_QUERY_LOSS_INFO = "BP-P-QUERY-LOSS-INFO";
    String K_OUTPUT_FMT = "SCZ01";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPOT1820_WS_DATA WS_DATA = new BPOT1820_WS_DATA();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    CICQACRI CICQACRI = new CICQACRI();
    BPCPLOSS BPCPLOSS = new BPCPLOSS();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCPRGST BPCPRGST = new BPCPRGST();
    CICOACRB CICOACRB = new CICOACRB();
    BPCPORUP BPCPORUP = new BPCPORUP();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB1820_AWA_1820 BPB1820_AWA_1820;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT1820 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1820_AWA_1820>");
        BPB1820_AWA_1820 = (BPB1820_AWA_1820) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
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
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, BPB1820_AWA_1820.LOS_ORG);
        if (BPB1820_AWA_1820.LOS_ORG != 0) {
            if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != BPB1820_AWA_1820.LOS_ORG) {
                BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                S000_CALL_BPZPQORG();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCPQORG.TYP);
                if (BPCPQORG.TYP.equalsIgnoreCase("01") 
                    || BPCPQORG.TYP.equalsIgnoreCase("02") 
                    || BPCPQORG.TYP.equalsIgnoreCase("03")) {
                } else {
                    IBS.init(SCCGWA, BPCPORUP);
                    BPCPORUP.DATA_INFO.BR = BPB1820_AWA_1820.LOS_ORG;
                    S000_CALL_BPZPORUP();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.SUPR_GRP[1-1].SUPR_BR);
                    CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.SUPR_GRP[2-1].SUPR_BR);
                    CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.SUPR_GRP[3-1].SUPR_BR);
                    if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH == BPCPORUP.DATA_INFO.SUPR_GRP[1-1].SUPR_BR 
                        || SCCGWA.COMM_AREA.BR_DP.TR_BRANCH == BPCPORUP.DATA_INFO.SUPR_GRP[2-1].SUPR_BR 
                        || SCCGWA.COMM_AREA.BR_DP.TR_BRANCH == BPCPORUP.DATA_INFO.SUPR_GRP[3-1].SUPR_BR) {
                    } else {
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_NO_AUTO_INQUERY);
                    }
                }
            }
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPLOSS);
        CEP.TRC(SCCGWA, BPB1820_AWA_1820.BV_TYP);
        CEP.TRC(SCCGWA, BPB1820_AWA_1820.BV_NO);
        CEP.TRC(SCCGWA, BPB1820_AWA_1820.AC);
        CEP.TRC(SCCGWA, BPB1820_AWA_1820.LOS_NO);
        CEP.TRC(SCCGWA, BPB1820_AWA_1820.ID_TYP);
        CEP.TRC(SCCGWA, BPB1820_AWA_1820.ID_NO);
        CEP.TRC(SCCGWA, BPB1820_AWA_1820.LOS_ORG);
        CEP.TRC(SCCGWA, BPB1820_AWA_1820.STA_DT);
        CEP.TRC(SCCGWA, BPB1820_AWA_1820.END_DT);
        BPCPLOSS.OUTPUT_FMT = K_OUTPUT_FMT;
        BPCPLOSS.DATA_INFO.BV_TYP = BPB1820_AWA_1820.BV_TYP;
        BPCPLOSS.DATA_INFO.BV_NO = BPB1820_AWA_1820.BV_NO;
        BPCPLOSS.DATA_INFO.AC = BPB1820_AWA_1820.AC;
        BPCPLOSS.DATA_INFO.LOS_NO = BPB1820_AWA_1820.LOS_NO;
        BPCPLOSS.DATA_INFO.ID_TYP = BPB1820_AWA_1820.ID_TYP;
        BPCPLOSS.DATA_INFO.ID_NO = BPB1820_AWA_1820.ID_NO;
        BPCPLOSS.DATA_INFO.LOS_ORG = BPB1820_AWA_1820.LOS_ORG;
        BPCPLOSS.DATA_INFO.STA_DT = BPB1820_AWA_1820.STA_DT;
        BPCPLOSS.DATA_INFO.END_DT = BPB1820_AWA_1820.END_DT;
        BPCPLOSS.DATA_INFO.CI_CNM = BPB1820_AWA_1820.CI_CNM;
        BPCPLOSS.DATA_INFO.STS = BPB1820_AWA_1820.STS;
        BPCPLOSS.INFO.FUNC = 'B';
        if (BPB1820_AWA_1820.LOS_NO.trim().length() > 0) {
            BPCPLOSS.INFO.INDEX_FLG = "8";
        } else if (BPB1820_AWA_1820.AC.trim().length() > 0) {
            BPCPLOSS.INFO.INDEX_FLG = "3";
        } else if (BPB1820_AWA_1820.BV_TYP != ' ') {
            BPCPLOSS.INFO.INDEX_FLG = "4";
        } else if (BPB1820_AWA_1820.ID_TYP.trim().length() > 0 
                && BPB1820_AWA_1820.ID_NO.trim().length() > 0) {
            BPCPLOSS.INFO.INDEX_FLG = "5";
        } else if (BPB1820_AWA_1820.LOS_ORG != 0) {
            BPCPLOSS.INFO.INDEX_FLG = "6";
        } else {
            if (BPB1820_AWA_1820.BV_TYP == ' ' 
                && BPB1820_AWA_1820.BV_NO.trim().length() == 0 
                && BPB1820_AWA_1820.AC.trim().length() == 0 
                && BPB1820_AWA_1820.LOS_NO.trim().length() == 0 
                && BPB1820_AWA_1820.ID_TYP.trim().length() == 0 
                && BPB1820_AWA_1820.ID_NO.trim().length() == 0 
                && BPB1820_AWA_1820.CI_CNM.trim().length() == 0 
                && BPB1820_AWA_1820.LOS_ORG == 0 
                && BPB1820_AWA_1820.LOS_DT == 0) {
                BPCPLOSS.DATA_INFO.LOS_ORG = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPCPLOSS.INFO.INDEX_FLG = "6";
            } else {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_NO_INQUERY);
            }
        }
        S000_CALL_BPZPLOSS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPLOSS.DATA_INFO.LOS_DT);
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_INQ_ACR_INF, CICQACRI);
    }
    public void S000_CALL_BPZPLOSS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_QUERY_LOSS_INFO, BPCPLOSS);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
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
