package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT3000 {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    DBParm LNTPPRP_RD;
    char K_PRINCIPAL = 'P';
    char K_PRINCIINT = 'I';
    LNOT3000_WS_MSGID WS_MSGID = new LNOT3000_WS_MSGID();
    short WS_MSG_INFO = 0;
    LNCXP10 LNCXP10 = new LNCXP10();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCFMT SCCFMT = new SCCFMT();
    LNCSPMAJ LNCSPMAJ = new LNCSPMAJ();
    LNCSSTBL LNCSSTBL = new LNCSSTBL();
    LNCSPMHQ LNCSPMHQ = new LNCSPMHQ();
    LNCRCONT LNCRCONT = new LNCRCONT();
    LNRCONT LNRCONT = new LNRCONT();
    LNCPCFTA LNCPCFTA = new LNCPCFTA();
    LNRPPRP LNRPPRP = new LNRPPRP();
    BPCFTLPM BPCFTLPM = new BPCFTLPM();
    SCCGWA SCCGWA;
    LNB3000_AWA_3000 LNB3000_AWA_3000;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "LNOT3000 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB3000_AWA_3000>");
        LNB3000_AWA_3000 = (LNB3000_AWA_3000) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_CALL_SVR_PROCESS();
        CEP.TRC(SCCGWA, LNCSPMAJ.COMM_DATA.A_B_RP_M);
        if (LNCSPMAJ.COMM_DATA.A_B_RP_M == '6' 
            || LNCSPMAJ.COMM_DATA.A_B_RP_M == '7' 
            || LNCSPMAJ.COMM_DATA.A_B_RP_M == '8' 
            || LNCSPMAJ.COMM_DATA.A_B_RP_M == 'A') {
        } else {
            B300_OUTPUT_PROC();
        }
        if (LNCSPMAJ.COMM_DATA.A_B_RP_M == '6') {
            B400_REPY_PLAN_ADD_TODO();
        } else {
        }
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B011_CHECK_ICTL_STS();
        }
        R000_CHECK_PROG();
    }
    public void R000_CHECK_PROG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRCONT);
        IBS.init(SCCGWA, LNRCONT);
        LNCRCONT.FUNC = 'I';
        LNRCONT.KEY.CONTRACT_NO = LNB3000_AWA_3000.CTA_NO;
        S000_CALL_LNZRCONT();
        IBS.init(SCCGWA, LNCPCFTA);
        LNCPCFTA.BR_GP[1-1].BR = LNRCONT.BOOK_BR;
        LNCPCFTA.BR_GP[2-1].BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_LNZPCFTA();
        if (LNCPCFTA.FTA_FLG == 'Y') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_NO_TRAN_FTA_NOT_SAME, WS_MSGID);
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, LNRPPRP);
        LNRPPRP.KEY.CONTRACT_NO = LNB3000_AWA_3000.CTA_NO;
        LNRPPRP.STATUS = '1';
        T000_READ_REC_PROC();
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_EXISTS_RECORD, WS_MSGID);
            S000_ERR_MSG_PROC();
        }
        if ((LNB3000_AWA_3000.A_P_UNIT == 'Y' 
            || LNB3000_AWA_3000.A_P_UNIT == 'M' 
            || LNB3000_AWA_3000.A_P_UNIT == 'W' 
            || LNB3000_AWA_3000.A_P_UNIT == 'D' 
            || LNB3000_AWA_3000.A_P_UNIT == ' ') 
            && (LNB3000_AWA_3000.A_I_UNIT == 'Y' 
            || LNB3000_AWA_3000.A_I_UNIT == 'M' 
            || LNB3000_AWA_3000.A_I_UNIT == 'W' 
            || LNB3000_AWA_3000.A_I_UNIT == 'D' 
            || LNB3000_AWA_3000.A_I_UNIT == ' ') 
            && (LNB3000_AWA_3000.A_O_UNIT == 'Y' 
            || LNB3000_AWA_3000.A_O_UNIT == 'M' 
            || LNB3000_AWA_3000.A_O_UNIT == 'W' 
            || LNB3000_AWA_3000.A_O_UNIT == 'D' 
            || LNB3000_AWA_3000.A_O_UNIT == ' ')) {
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_UNIT, WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void B200_CALL_SVR_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSPMAJ);
        LNCSPMAJ.COMM_DATA.CTA_NO = LNB3000_AWA_3000.CTA_NO;
        CEP.TRC(SCCGWA, LNB3000_AWA_3000.A_B_RP_M);
        CEP.TRC(SCCGWA, LNCSPMAJ.COMM_DATA.A_B_RP_M);
        LNCSPMAJ.COMM_DATA.A_B_RP_M = LNB3000_AWA_3000.A_B_RP_M;
        LNCSPMAJ.COMM_DATA.A_P_TYP = LNB3000_AWA_3000.A_P_TYP;
        LNCSPMAJ.COMM_DATA.A_P_PERD = LNB3000_AWA_3000.A_P_PERD;
        LNCSPMAJ.COMM_DATA.A_P_UNIT = LNB3000_AWA_3000.A_P_UNIT;
        LNCSPMAJ.COMM_DATA.A_I_TYP = LNB3000_AWA_3000.A_I_TYP;
        LNCSPMAJ.COMM_DATA.A_I_PERD = LNB3000_AWA_3000.A_I_PERD;
        LNCSPMAJ.COMM_DATA.A_I_UNIT = LNB3000_AWA_3000.A_I_UNIT;
        LNCSPMAJ.COMM_DATA.A_INT_ED = LNB3000_AWA_3000.A_INT_ED;
        LNCSPMAJ.COMM_DATA.A_B_PERD = LNB3000_AWA_3000.A_B_PERD;
        if (LNB3000_AWA_3000.A_O_TYP == ' ') {
            LNCSPMAJ.COMM_DATA.A_O_TYP = '1';
        } else {
            LNCSPMAJ.COMM_DATA.A_O_TYP = LNB3000_AWA_3000.A_O_TYP;
        }
        LNCSPMAJ.COMM_DATA.A_O_PERD = LNB3000_AWA_3000.A_O_PERD;
        LNCSPMAJ.COMM_DATA.A_O_UNIT = LNB3000_AWA_3000.A_O_UNIT;
        LNCSPMAJ.COMM_DATA.TRAN_SEQ = LNB3000_AWA_3000.TRAN_SEQ;
        CEP.TRC(SCCGWA, LNB3000_AWA_3000.TRAN_SEQ);
        CEP.TRC(SCCGWA, LNB3000_AWA_3000.A_INT_ED);
        CEP.TRC(SCCGWA, LNCSPMAJ.COMM_DATA.A_INT_ED);
        S000_CALL_LNZSPMAJ();
    }
    public void B300_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSPMHQ);
        LNCSPMHQ.COMM_DATA.CTA_NO = LNB3000_AWA_3000.CTA_NO;
        S000_CALL_LNZSPMHQ();
        if (LNCSPMHQ.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCSPMHQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_MSGID);
            S000_ERR_MSG_PROC();
        }
        B310_MOVE_DATA();
        B320_OUTPUT();
    }
    public void B310_MOVE_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCXP10);
        LNCXP10.CTA_NO = LNB3000_AWA_3000.CTA_NO;
        LNCXP10.A_B_RP_M = LNB3000_AWA_3000.A_B_RP_M;
        LNCXP10.A_P_TYP = LNB3000_AWA_3000.A_P_TYP;
        LNCXP10.A_P_PERD = LNCSPMHQ.COMM_DATA.P_PERD;
        LNCXP10.A_P_UNIT = LNCSPMHQ.COMM_DATA.P_UNIT;
        LNCXP10.A_P_GRA = LNCSPMHQ.COMM_DATA.P_GRA;
        LNCXP10.A_I_TYP = LNB3000_AWA_3000.A_I_TYP;
        LNCXP10.A_I_PERD = LNCSPMHQ.COMM_DATA.I_PERD;
        LNCXP10.A_I_UNIT = LNCSPMHQ.COMM_DATA.I_UNIT;
        LNCXP10.A_INT_ED = LNCSPMHQ.COMM_DATA.INT_ED;
        LNCXP10.A_B_PERD = LNCSPMHQ.COMM_DATA.B_PERD;
        LNCXP10.A_O_TYP = LNB3000_AWA_3000.A_O_TYP;
        LNCXP10.A_O_PERD = LNCSPMHQ.COMM_DATA.CAL_PERD;
        LNCXP10.A_O_UNIT = LNCSPMHQ.COMM_DATA.CAL_UNIT;
    }
    public void B320_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "LN300";
        SCCFMT.DATA_PTR = LNCXP10;
        SCCFMT.DATA_LEN = 67;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B400_REPY_PLAN_ADD_TODO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLPM);
        BPCFTLPM.OP_CODE = 'A';
        BPCFTLPM.TLR = SCCGWA.COMM_AREA.TL_ID;
        if ("1".trim().length() == 0) BPCFTLPM.TX_COUNT = 0;
        else BPCFTLPM.TX_COUNT = Short.parseShort("1");
        BPCFTLPM.BUSS_TYP = "LN";
        S000_CALL_BPZFTLPM();
        CEP.TRC(SCCGWA, "REPY-PLAN-ADD-COMPLETE");
    }
    public void S000_CALL_BPZFTLPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-TLR-PEND-MGM", BPCFTLPM);
        if (BPCFTLPM.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFTLPM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_LNZSPMAJ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-PAYMTH-ADJ", LNCSPMAJ);
        if (LNCSPMAJ.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCSPMAJ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_LNZPCFTA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-P-CHK-FTA-TYP", LNCPCFTA);
        if (LNCPCFTA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCPCFTA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_LNZRCONT() throws IOException,SQLException,Exception {
        LNCRCONT.REC_PTR = LNRCONT;
        LNCRCONT.REC_LEN = 416;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTCONT", LNCRCONT, true);
        if (LNCRCONT.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRCONT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void B011_CHECK_ICTL_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSSTBL);
        LNCSSTBL.CON_NO = LNB3000_AWA_3000.CTA_NO;
        LNCSSTBL.TR_CODE = "" + SCCGWA.COMM_AREA.TR_ID.TR_CODE;
        JIBS_tmp_int = LNCSSTBL.TR_CODE.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) LNCSSTBL.TR_CODE = "0" + LNCSSTBL.TR_CODE;
        CEP.TRC(SCCGWA, LNCSSTBL.TR_CODE);
        S000_CALL_LNZSSTBL();
    }
    public void S000_CALL_LNZSPMHQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-PAYMTH-INQ", LNCSPMHQ);
    }
    public void T000_READ_REC_PROC() throws IOException,SQLException,Exception {
        LNTPPRP_RD = new DBParm();
        LNTPPRP_RD.TableName = "LNTPPRP";
        LNTPPRP_RD.where = "CONTRACT_NO = :LNRPPRP.KEY.CONTRACT_NO "
            + "AND STATUS = :LNRPPRP.STATUS";
        LNTPPRP_RD.fst = true;
        IBS.READ(SCCGWA, LNRPPRP, this, LNTPPRP_RD);
    }
    public void S000_CALL_LNZSSTBL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-CHECK-CI-STS", LNCSSTBL);
        if (LNCSSTBL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCSSTBL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0], WS_MSG_INFO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
