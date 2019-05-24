package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4620 {
    String CPN_S_FCLOSE_ORG = "BP-S-FCLOSE-ORG     ";
    String BP_P_INQ_ORG_REL = "BP-P-INQ-ORG-REL";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_REL_BR = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSFORG BPCSFORG = new BPCSFORG();
    BPCPORUP BPCPORUP = new BPCPORUP();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCPQORR BPCPQORR = new BPCPQORR();
    SCCGWA SCCGWA;
    BPB4600_AWA_4600 BPB4600_AWA_4600;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4620 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4600_AWA_4600>");
        BPB4600_AWA_4600 = (BPB4600_AWA_4600) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B010_FCLOSE_ORG_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB4600_AWA_4600.BR != 0 
            && (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != BPB4600_AWA_4600.BR)) {
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
            BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            S000_CALL_BPZPQORG();
            CEP.TRC(SCCGWA, BPCPQORG.TYP);
            CEP.TRC(SCCGWA, BPCPQORG.SUPR_BR);
            CEP.TRC(SCCGWA, BPCPQORG.LVL);
            if ((BPCPQORG.TYP.equalsIgnoreCase("01") 
                || BPCPQORG.TYP.equalsIgnoreCase("02") 
                || BPCPQORG.TYP.equalsIgnoreCase("03"))) {
            } else {
                IBS.init(SCCGWA, BPCPORUP);
                BPCPORUP.DATA_INFO.BR = BPB4600_AWA_4600.BR;
                S000_CALL_BPZPORUP();
                CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.SUPR_GRP[1-1].SUPR_BR);
                CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.SUPR_GRP[2-1].SUPR_BR);
                CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.SUPR_GRP[3-1].SUPR_BR);
                if (BPCPQORG.SUPR_BR == BPCPORUP.DATA_INFO.SUPR_GRP[1-1].SUPR_BR 
                    || BPCPQORG.SUPR_BR == BPCPORUP.DATA_INFO.SUPR_GRP[2-1].SUPR_BR 
                    || BPCPQORG.SUPR_BR == BPCPORUP.DATA_INFO.SUPR_GRP[3-1].SUPR_BR) {
                    CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.LVL);
                    if (BPCPQORG.LVL > BPCPORUP.DATA_INFO.LVL) {
                    } else {
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_NO_AUTO_CLODS_ORG);
                    }
                } else {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_NO_AUTO_CLODS_ORG);
                }
            }
        }
        if (BPB4600_AWA_4600.BR != 0) {
            IBS.init(SCCGWA, BPCPQORR);
            BPCPQORR.TYP = "12";
            BPCPQORR.BR = BPB4600_AWA_4600.BR;
            BPCPQORR.CHECK_IND = '1';
            S000_CALL_BPZPQORR();
        }
    }
    public void B010_FCLOSE_ORG_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSFORG);
        BPCSFORG.BR = BPB4600_AWA_4600.BR;
        CEP.TRC(SCCGWA, BPCSFORG.BR);
        S000_CALL_BPZSFORG();
        CEP.TRC(SCCGWA, WS_REL_BR);
        if (WS_REL_BR != 0) {
            IBS.init(SCCGWA, BPCSFORG);
            BPCSFORG.BR = WS_REL_BR;
            CEP.TRC(SCCGWA, BPCSFORG.BR);
            S000_CALL_BPZSFORG();
        }
    }
    public void S000_CALL_BPZPORUP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-SUPR-ORG", BPCPORUP);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_ORG_NOTFND);
        }
    }
    public void S000_CALL_BPZSFORG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_S_FCLOSE_ORG;
        SCCCALL.COMMAREA_PTR = BPCSFORG;
        SCCCALL.ERR_FLDNO = BPB4600_AWA_4600.TLR_NO;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_CALL_BPZPQORR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, BP_P_INQ_ORG_REL, BPCPQORR);
        CEP.TRC(SCCGWA, BPCPQORR.RC.RC_CODE);
        if (BPCPQORR.RC.RC_CODE != 0) {
            if (BPCPQORR.RC.RC_CODE != 1505) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORR.RC);
                S000_ERR_MSG_PROC();
            }
        } else {
            CEP.TRC(SCCGWA, BPCPQORR.REL_BR);
            WS_REL_BR = BPCPQORR.REL_BR;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
