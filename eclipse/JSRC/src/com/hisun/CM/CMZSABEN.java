package com.hisun.CM;

import com.hisun.SC.*;
import com.hisun.TC.*;

import java.io.IOException;
import java.sql.SQLException;

public class CMZSABEN {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm TCTTEST_RD;
    brParm CMTBSPM_BR = new brParm();
    String K_SYS_ERR = "SC6001";
    String PGM_SCSSCLDT = "SCSSCLDT";
    int WK_TIG_TIME = 080000;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_I = 0;
    int WS_J = 0;
    int WS_CNT = 0;
    int WS_DAY_CNT = 0;
    short WS_R = 0;
    String WS_ACO_NO = " ";
    String WS_DDAC_STSW = " ";
    String WS_TM_X = " ";
    short WS_TM_2 = 0;
    short WS_RC = 0;
    short WS_RC_DISP = 0;
    String WS_ABEND_CUS_AC = "                                ";
    CMZSABEN_WS_ABEND_AMT_INF WS_ABEND_AMT_INF = new CMZSABEN_WS_ABEND_AMT_INF();
    CMZSABEN_WS_ABEND_DEFAU_AMT_INF WS_ABEND_DEFAU_AMT_INF = new CMZSABEN_WS_ABEND_DEFAU_AMT_INF();
    char WS_CMTBSPM_FLG = ' ';
    char WS_TCTTEST_FLG = ' ';
    char WS_ABEND_FLG = ' ';
    char WS_ABEND_DEFAU_FLG = ' ';
    CMCMSG_ERROR_MSG CMCMSG_ERROR_MSG = new CMCMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    CMRBSPM CMRBSPM = new CMRBSPM();
    TCRTEST TCRTEST = new TCRTEST();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCBINF SCCBINF;
    CMCSABEN CMCSABEN;
    public void MP(SCCGWA SCCGWA, CMCSABEN CMCSABEN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CMCSABEN = CMCSABEN;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CMZSABEN return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CMRBSPM);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_INPUT_CHECK_PROC();
        if (CMCSABEN.TXN_AMT >= 0) {
            B200_ABEND_MAIN_PROC();
        }
    }
    public void B100_INPUT_CHECK_PROC() throws IOException,SQLException,Exception {
    }
    public void B200_ABEND_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TCRTEST);
        TCRTEST.KEY.REQ_CHNL = SCCGWA.COMM_AREA.REQ_SYSTEM;
        TCRTEST.KEY.TR_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        T000_READ_TCTTEST();
        if (WS_TCTTEST_FLG == 'Y' 
            && TCRTEST.OPT_FLG == 'A') {
            B300_CHK_ABEND_COND_PROC();
            CEP.TRC(SCCGWA, WS_ABEND_FLG);
            CEP.TRC(SCCGWA, WS_ABEND_CUS_AC);
            if (((WS_ABEND_FLG != '1' 
                && WS_ABEND_FLG != '2' 
                && WS_ABEND_FLG != '3')) 
                && WS_ABEND_DEFAU_FLG == 'Y') {
                if (((CMCSABEN.TXN_AMT >= WS_ABEND_DEFAU_AMT_INF.WS_ABEND_DEFAU_AMT_ARREAY[1-1].WS_ABEND_DEFAU_AMT_BEG 
                        && CMCSABEN.TXN_AMT <= WS_ABEND_DEFAU_AMT_INF.WS_ABEND_DEFAU_AMT_ARREAY[1-1].WS_ABEND_DEFAU_AMT_END) 
                        || (WS_ABEND_DEFAU_AMT_INF.WS_ABEND_DEFAU_AMT_ARREAY[1-1].WS_ABEND_DEFAU_AMT_BEG == 0 
                        && WS_ABEND_DEFAU_AMT_INF.WS_ABEND_DEFAU_AMT_ARREAY[1-1].WS_ABEND_DEFAU_AMT_END == 99999))) {
                    WS_ABEND_FLG = '1';
                } else if (((CMCSABEN.TXN_AMT >= WS_ABEND_DEFAU_AMT_INF.WS_ABEND_DEFAU_AMT_ARREAY[2-1].WS_ABEND_DEFAU_AMT_BEG 
                        && CMCSABEN.TXN_AMT <= WS_ABEND_DEFAU_AMT_INF.WS_ABEND_DEFAU_AMT_ARREAY[2-1].WS_ABEND_DEFAU_AMT_END) 
                        || (WS_ABEND_DEFAU_AMT_INF.WS_ABEND_DEFAU_AMT_ARREAY[2-1].WS_ABEND_DEFAU_AMT_BEG == 0 
                        && WS_ABEND_DEFAU_AMT_INF.WS_ABEND_DEFAU_AMT_ARREAY[2-1].WS_ABEND_DEFAU_AMT_END == 99999))) {
                    WS_ABEND_FLG = '2';
                } else if (((CMCSABEN.TXN_AMT >= WS_ABEND_DEFAU_AMT_INF.WS_ABEND_DEFAU_AMT_ARREAY[3-1].WS_ABEND_DEFAU_AMT_BEG 
                        && CMCSABEN.TXN_AMT <= WS_ABEND_DEFAU_AMT_INF.WS_ABEND_DEFAU_AMT_ARREAY[3-1].WS_ABEND_DEFAU_AMT_END) 
                        || (WS_ABEND_DEFAU_AMT_INF.WS_ABEND_DEFAU_AMT_ARREAY[3-1].WS_ABEND_DEFAU_AMT_BEG == 0 
                        && WS_ABEND_DEFAU_AMT_INF.WS_ABEND_DEFAU_AMT_ARREAY[3-1].WS_ABEND_DEFAU_AMT_END == 99999))) {
                    WS_ABEND_FLG = '3';
                } else {
                }
            }
            CEP.TRC(SCCGWA, WS_ABEND_FLG);
            B500_TRIG_ABEND_PROCESS();
        }
    }
    public void B300_CHK_ABEND_COND_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CMRBSPM);
        IBS.init(SCCGWA, WS_ABEND_DEFAU_AMT_INF);
        CMRBSPM.KEY.AP_TYPE = "ZFYC";
        T000_STARTBR_CMTBSPM();
        T000_READNEXT_CMTBSPM();
        while (WS_CMTBSPM_FLG != 'N' 
            && (WS_ABEND_FLG != '1' 
            && WS_ABEND_FLG != '2' 
            && WS_ABEND_FLG != '3') 
            && WS_I <= 100000) {
            WS_I += 1;
            WS_ABEND_CUS_AC = " ";
            IBS.init(SCCGWA, WS_ABEND_AMT_INF);
            if (CMRBSPM.IN_DATA == null) CMRBSPM.IN_DATA = "";
            JIBS_tmp_int = CMRBSPM.IN_DATA.length();
            for (int i=0;i<999-JIBS_tmp_int;i++) CMRBSPM.IN_DATA += " ";
            WS_ABEND_CUS_AC = CMRBSPM.IN_DATA.substring(0, 32);
            if (CMRBSPM.TR_DATA == null) CMRBSPM.TR_DATA = "";
            JIBS_tmp_int = CMRBSPM.TR_DATA.length();
            for (int i=0;i<999-JIBS_tmp_int;i++) CMRBSPM.TR_DATA += " ";
            IBS.CPY2CLS(SCCGWA, CMRBSPM.TR_DATA.substring(0, 66), WS_ABEND_AMT_INF);
            if (WS_ABEND_CUS_AC == null) WS_ABEND_CUS_AC = "";
            JIBS_tmp_int = WS_ABEND_CUS_AC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) WS_ABEND_CUS_AC += " ";
            if (WS_ABEND_CUS_AC.substring(0, 1).equalsIgnoreCase("X")) {
                WS_ABEND_DEFAU_FLG = 'Y';
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ABEND_AMT_INF);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ABEND_DEFAU_AMT_INF);
            }
            B400_CHK_ABEND_MATCH_COND();
            T000_READNEXT_CMTBSPM();
        }
        T000_ENDBR_CMTBSPM();
    }
    public void B400_CHK_ABEND_MATCH_COND() throws IOException,SQLException,Exception {
        WS_J = 1;
        CEP.TRC(SCCGWA, CMCSABEN.TXN_AMT);
        while (WS_J <= 10 
            && (CMCSABEN.TXN_INF[WS_J-1].AC.trim().length() != 0 
            && CMCSABEN.TXN_INF[WS_J-1].AC.charAt(0) != 0X00) 
            && (WS_ABEND_FLG != '1' 
            && WS_ABEND_FLG != '2' 
            && WS_ABEND_FLG != '3')) {
            if (CMCSABEN.TXN_INF[WS_J-1].AC.equalsIgnoreCase(WS_ABEND_CUS_AC)) {
                CEP.TRC(SCCGWA, WS_J);
                CEP.TRC(SCCGWA, CMCSABEN.TXN_INF[WS_J-1].AC);
                CEP.TRC(SCCGWA, WS_ABEND_CUS_AC);
                if (((CMCSABEN.TXN_AMT >= WS_ABEND_AMT_INF.WS_ABEND_AMT_ARREAY[1-1].WS_ABEND_AMT_BEG 
                        && CMCSABEN.TXN_AMT <= WS_ABEND_AMT_INF.WS_ABEND_AMT_ARREAY[1-1].WS_ABEND_AMT_END) 
                        || (WS_ABEND_DEFAU_AMT_INF.WS_ABEND_DEFAU_AMT_ARREAY[1-1].WS_ABEND_DEFAU_AMT_BEG == 0 
                        && WS_ABEND_DEFAU_AMT_INF.WS_ABEND_DEFAU_AMT_ARREAY[1-1].WS_ABEND_DEFAU_AMT_END == 99999))) {
                    WS_ABEND_FLG = '1';
                } else if (((CMCSABEN.TXN_AMT >= WS_ABEND_AMT_INF.WS_ABEND_AMT_ARREAY[2-1].WS_ABEND_AMT_BEG 
                        && CMCSABEN.TXN_AMT <= WS_ABEND_AMT_INF.WS_ABEND_AMT_ARREAY[2-1].WS_ABEND_AMT_END) 
                        || (WS_ABEND_DEFAU_AMT_INF.WS_ABEND_DEFAU_AMT_ARREAY[2-1].WS_ABEND_DEFAU_AMT_BEG == 0 
                        && WS_ABEND_DEFAU_AMT_INF.WS_ABEND_DEFAU_AMT_ARREAY[2-1].WS_ABEND_DEFAU_AMT_END == 99999))) {
                    WS_ABEND_FLG = '2';
                } else if (((CMCSABEN.TXN_AMT >= WS_ABEND_AMT_INF.WS_ABEND_AMT_ARREAY[3-1].WS_ABEND_AMT_BEG 
                        && CMCSABEN.TXN_AMT <= WS_ABEND_AMT_INF.WS_ABEND_AMT_ARREAY[3-1].WS_ABEND_AMT_END) 
                        || (WS_ABEND_DEFAU_AMT_INF.WS_ABEND_DEFAU_AMT_ARREAY[3-1].WS_ABEND_DEFAU_AMT_BEG == 0 
                        && WS_ABEND_DEFAU_AMT_INF.WS_ABEND_DEFAU_AMT_ARREAY[3-1].WS_ABEND_DEFAU_AMT_END == 99999))) {
                    WS_ABEND_FLG = '3';
                } else {
                }
            }
            WS_J += 1;
        }
        CEP.TRC(SCCGWA, WS_ABEND_FLG);
    }
    public void B500_TRIG_ABEND_PROCESS() throws IOException,SQLException,Exception {
        if (WS_ABEND_FLG == '1') {
            CEP.TRC(SCCGWA, "TRIG DELAY SUCC");
            Thread.sleep(000060);
        } else if (WS_ABEND_FLG == '2') {
            CEP.TRC(SCCGWA, "TRIG DELAY EXCEP");
            Thread.sleep(000060);
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_M.MSG_ID = K_SYS_ERR;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_M);
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        } else if (WS_ABEND_FLG == '3') {
            CEP.TRC(SCCGWA, "TRIG DIREC EXCEP");
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_M.MSG_ID = K_SYS_ERR;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_M);
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        } else {
        }
    }
    public void T000_READ_TCTTEST() throws IOException,SQLException,Exception {
        TCTTEST_RD = new DBParm();
        TCTTEST_RD.TableName = "TCTTEST";
        IBS.READ(SCCGWA, TCRTEST, TCTTEST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TCTTEST_FLG = 'Y';
        } else {
            WS_TCTTEST_FLG = 'N';
        }
    }
    public void T000_STARTBR_CMTBSPM() throws IOException,SQLException,Exception {
        CMTBSPM_BR.rp = new DBParm();
        CMTBSPM_BR.rp.TableName = "CMTBSPM";
        CMTBSPM_BR.rp.where = "AP_TYPE = :CMRBSPM.KEY.AP_TYPE";
        IBS.STARTBR(SCCGWA, CMRBSPM, this, CMTBSPM_BR);
    }
    public void T000_READNEXT_CMTBSPM() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CMRBSPM, this, CMTBSPM_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CMTBSPM_FLG = 'Y';
        } else {
            WS_CMTBSPM_FLG = 'N';
        }
    }
    public void T000_ENDBR_CMTBSPM() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CMTBSPM_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
