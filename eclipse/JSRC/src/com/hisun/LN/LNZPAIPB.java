package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZPAIPB {
    brParm LNTPAIP_BR = new brParm();
    boolean pgmRtn = false;
    char LNZPAIPB_FILLER1 = ' ';
    short WS_I = 0;
    String WS_JJJ = " ";
    int WS_PHS_CNT = 20;
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    LNRPAIP LNRPAIP = new LNRPAIP();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCCLDT SCCCLDT = new SCCCLDT();
    LNCICTLM LNCICTLM = new LNCICTLM();
    SCCGWA SCCGWA;
    LNCPAIPB LNCPAIPB;
    public void MP(SCCGWA SCCGWA, LNCPAIPB LNCPAIPB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCPAIPB = LNCPAIPB;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZPAIPB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNCPAIPB.RC.RC_APP = "LN";
        LNCPAIPB.RC.RC_RTNCODE = 0;
        CEP.TRC(SCCGWA, LNCPAIPB.COMM_DATA.LN_AC);
        CEP.TRC(SCCGWA, LNCPAIPB.COMM_DATA.SUF_NO);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK();
        if (pgmRtn) return;
        B200_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B000_CHECK() throws IOException,SQLException,Exception {
        if (LNCPAIPB.COMM_DATA.LN_AC.equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, LNCPAIPB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B200_MAIN_PROC() throws IOException,SQLException,Exception {
        WS_I = 0;
        IBS.init(SCCGWA, LNRPAIP);
        LNRPAIP.KEY.CONTRACT_NO = LNCPAIPB.COMM_DATA.LN_AC;
        if (LNCPAIPB.COMM_DATA.SUF_NO.trim().length() == 0) LNRPAIP.KEY.SUB_CTA_NO = 0;
        else LNRPAIP.KEY.SUB_CTA_NO = Integer.parseInt(LNCPAIPB.COMM_DATA.SUF_NO);
        LNRPAIP.KEY.PHS_NO = 0;
        T00_STARTBR_LNTPAIP();
        if (pgmRtn) return;
        T00_READNEXT_LNTPAIP();
        if (pgmRtn) return;
        while ((WS_I < WS_PHS_CNT) 
            && WS_FOUND_FLG == 'Y') {
            WS_I += 1;
            LNCPAIPB.COMM_DATA.PHS_DATA[WS_I-1].PHS_NO = LNRPAIP.KEY.PHS_NO;
            LNCPAIPB.COMM_DATA.PHS_DATA[WS_I-1].INST_MTH = LNRPAIP.INST_MTH;
            LNCPAIPB.COMM_DATA.PHS_DATA[WS_I-1].PERD = LNRPAIP.PERD;
            LNCPAIPB.COMM_DATA.PHS_DATA[WS_I-1].PERD_UNIT = LNRPAIP.PERD_UNIT;
            LNCPAIPB.COMM_DATA.PHS_DATA[WS_I-1].PHS_INST_AMT = LNRPAIP.PHS_INST_AMT;
            LNCPAIPB.COMM_DATA.PHS_DATA[WS_I-1].PHS_PRIN_AMT = LNRPAIP.PHS_PRIN_AMT;
            LNCPAIPB.COMM_DATA.PHS_DATA[WS_I-1].PHS_TOT_TERM = LNRPAIP.PHS_TOT_TERM;
            LNCPAIPB.COMM_DATA.PHS_DATA[WS_I-1].PHS_REM_PRIN_AMT = LNRPAIP.PHS_REM_PRIN_AMT;
            LNCPAIPB.COMM_DATA.PHS_DATA[WS_I-1].PHS_CAL_TERM = LNRPAIP.PHS_CAL_TERM;
            LNCPAIPB.COMM_DATA.PHS_DATA[WS_I-1].PHS_CMP_TERM = LNRPAIP.PHS_CMP_TERM;
            LNCPAIPB.COMM_DATA.PHS_DATA[WS_I-1].CUR_INST_AMT = LNRPAIP.CUR_INST_AMT;
            LNCPAIPB.COMM_DATA.PHS_DATA[WS_I-1].CUR_INST_IRAT = LNRPAIP.CUR_INST_IRAT;
            T00_READNEXT_LNTPAIP();
            if (pgmRtn) return;
        }
        T00_ENDBR_LNTPAIP();
        if (pgmRtn) return;
        LNCPAIPB.COMM_DATA.PHS_CNT = WS_I;
        CEP.TRC(SCCGWA, LNCPAIPB.COMM_DATA.PHS_CNT);
    }
    public void T00_STARTBR_LNTPAIP() throws IOException,SQLException,Exception {
        LNTPAIP_BR.rp = new DBParm();
        LNTPAIP_BR.rp.TableName = "LNTPAIP";
        LNTPAIP_BR.rp.eqWhere = "SUB_CTA_NO";
        LNTPAIP_BR.rp.where = "PHS_NO >= :LNRPAIP.KEY.PHS_NO";
        LNTPAIP_BR.rp.order = "PHS_NO";
        IBS.STARTBR(SCCGWA, LNRPAIP, this, LNTPAIP_BR);
    }
    public void T00_READNEXT_LNTPAIP() throws IOException,SQLException,Exception {
        WS_FOUND_FLG = ' ';
        IBS.READNEXT(SCCGWA, LNRPAIP, this, LNTPAIP_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_FLG = 'Y';
        }
    }
    public void T00_ENDBR_LNTPAIP() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTPAIP_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCPAIPB.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCPAIPB=");
            CEP.TRC(SCCGWA, LNCPAIPB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
