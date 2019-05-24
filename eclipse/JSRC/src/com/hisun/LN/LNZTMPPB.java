package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZTMPPB {
    int JIBS_tmp_int;
    brParm LNTTMPP_BR = new brParm();
    DBParm LNTICTL_RD;
    boolean pgmRtn = false;
    String PGM_SCSSCKDT = "SCSSCKDT";
    char LNZTMPPB_FILLER1 = ' ';
    short WS_I = 0;
    short WS_JJJ = 0;
    int WS_PHS_CNT = 20;
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    LNRTMPP LNRTMPP = new LNRTMPP();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCCLDT SCCCLDT = new SCCCLDT();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNRICTL LNRICTL = new LNRICTL();
    SCCGWA SCCGWA;
    LNCTMPPB LNCTMPPB;
    public void MP(SCCGWA SCCGWA, LNCTMPPB LNCTMPPB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCTMPPB = LNCTMPPB;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZTMPPB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNCTMPPB.RC.RC_APP = "LN";
        LNCTMPPB.RC.RC_RTNCODE = 0;
        CEP.TRC(SCCGWA, LNCTMPPB.COMM_DATA.LN_AC);
        CEP.TRC(SCCGWA, LNCTMPPB.COMM_DATA.SUF_NO);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK();
        if (pgmRtn) return;
        B200_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B000_CHECK() throws IOException,SQLException,Exception {
        if (LNCTMPPB.COMM_DATA.LN_AC.equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, LNCTMPPB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNRICTL);
        LNRICTL.KEY.CONTRACT_NO = LNCTMPPB.COMM_DATA.LN_AC;
        T000_READ_LNTICTL();
        if (pgmRtn) return;
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(54 - 1, 54 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_CONNOT_BE_XBD, LNCTMPPB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B200_MAIN_PROC() throws IOException,SQLException,Exception {
        WS_I = 0;
        IBS.init(SCCGWA, LNRTMPP);
        LNRTMPP.KEY.TRAN_SEQ = LNCTMPPB.COMM_DATA.TRAN_SEQ;
        LNRTMPP.KEY.CONTRACT_NO = LNCTMPPB.COMM_DATA.LN_AC;
        if (LNCTMPPB.COMM_DATA.SUF_NO.trim().length() == 0) LNRTMPP.KEY.SUB_CTA_NO = 0;
        else LNRTMPP.KEY.SUB_CTA_NO = Integer.parseInt(LNCTMPPB.COMM_DATA.SUF_NO);
        LNRTMPP.KEY.PHS_NO = 0;
        T00_STARTBR_LNTTMPP();
        if (pgmRtn) return;
        T00_READNEXT_LNTTMPP();
        if (pgmRtn) return;
        while ((WS_I < WS_PHS_CNT) 
            && WS_FOUND_FLG == 'Y') {
            WS_I += 1;
            LNCTMPPB.COMM_DATA.PHS_DATA[WS_I-1].PHS_NO = LNRTMPP.KEY.PHS_NO;
            LNCTMPPB.COMM_DATA.PHS_DATA[WS_I-1].ACTION = LNRTMPP.ACTION;
            LNCTMPPB.COMM_DATA.PHS_DATA[WS_I-1].INST_MTH = LNRTMPP.INST_MTH;
            LNCTMPPB.COMM_DATA.PHS_DATA[WS_I-1].PERD = LNRTMPP.PERD;
            LNCTMPPB.COMM_DATA.PHS_DATA[WS_I-1].PERD_UNIT = LNRTMPP.PERD_UNIT;
            LNCTMPPB.COMM_DATA.PHS_DATA[WS_I-1].PHS_INST_AMT = LNRTMPP.PHS_INST_AMT;
            LNCTMPPB.COMM_DATA.PHS_DATA[WS_I-1].PHS_PRIN_AMT = LNRTMPP.PHS_PRIN_AMT;
            LNCTMPPB.COMM_DATA.PHS_DATA[WS_I-1].PHS_TOT_TERM = LNRTMPP.PHS_TOT_TERM;
            LNCTMPPB.COMM_DATA.PHS_DATA[WS_I-1].PHS_REM_PRIN_AMT = LNRTMPP.PHS_REM_PRIN_AMT;
            LNCTMPPB.COMM_DATA.PHS_DATA[WS_I-1].PHS_CAL_TERM = LNRTMPP.PHS_CAL_TERM;
            LNCTMPPB.COMM_DATA.PHS_DATA[WS_I-1].PHS_CMP_TERM = LNRTMPP.PHS_CMP_TERM;
            LNCTMPPB.COMM_DATA.PHS_DATA[WS_I-1].CUR_INST_AMT = LNRTMPP.CUR_INST_AMT;
            LNCTMPPB.COMM_DATA.PHS_DATA[WS_I-1].CUR_INST_IRAT = LNRTMPP.CUR_INST_IRAT;
            T00_READNEXT_LNTTMPP();
            if (pgmRtn) return;
        }
        T00_ENDBR_LNTTMPP();
        if (pgmRtn) return;
        LNCTMPPB.COMM_DATA.PHS_CNT = WS_I;
        CEP.TRC(SCCGWA, LNCTMPPB.COMM_DATA.PHS_CNT);
    }
    public void T00_STARTBR_LNTTMPP() throws IOException,SQLException,Exception {
        LNTTMPP_BR.rp = new DBParm();
        LNTTMPP_BR.rp.TableName = "LNTTMPP";
        LNTTMPP_BR.rp.eqWhere = "TRAN_SEQ";
        LNTTMPP_BR.rp.where = "PHS_NO >= :LNRTMPP.KEY.PHS_NO";
        LNTTMPP_BR.rp.order = "PHS_NO";
        IBS.STARTBR(SCCGWA, LNRTMPP, this, LNTTMPP_BR);
    }
    public void T00_READNEXT_LNTTMPP() throws IOException,SQLException,Exception {
        WS_FOUND_FLG = ' ';
        IBS.READNEXT(SCCGWA, LNRTMPP, this, LNTTMPP_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_FLG = 'Y';
        }
    }
    public void T00_ENDBR_LNTTMPP() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTTMPP_BR);
    }
    public void T000_READ_LNTICTL() throws IOException,SQLException,Exception {
        LNTICTL_RD = new DBParm();
        LNTICTL_RD.TableName = "LNTICTL";
        IBS.READ(SCCGWA, LNRICTL, LNTICTL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_ICTL_NOTFND, LNCTMPPB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCTMPPB.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCTMPPB=");
            CEP.TRC(SCCGWA, LNCTMPPB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
