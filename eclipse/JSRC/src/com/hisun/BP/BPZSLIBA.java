package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCOWA;

public class BPZSLIBA {
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZSTLVB";
    String CPN_R_TLR_MAINTAIN = "BP-R-TLR-MAINTAIN";
    String CPN_R_STARTBR_ALIB = "BP-R-STARTBR-ALIB";
    String CPN_R_ADW_TLVB = "BP-R-ADW-TLVB";
    String CPN_INQ_ORG_STATION = "BP-P-INQ-ORG-STATION";
    BPZSLIBA_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPZSLIBA_WS_TEMP_VARIABLE();
    String WS_ERR_MSG = " ";
    char WS_TBL_FLAG = ' ';
    char WS_FOUND1_FLG = ' ';
    char WS_FOUND2_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCTLVBF BPCTLVBF = new BPCTLVBF();
    BPCOTLIB BPCOTLIB = new BPCOTLIB();
    BPCRALIB BPCRALIB = new BPCRALIB();
    BPCOLVBO BPCOLVBO = new BPCOLVBO();
    BPCOALAO BPCOALAO = new BPCOALAO();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCPRGST BPCPRGST = new BPCPRGST();
    BPRALIB BPRALIB = new BPRALIB();
    BPRAPAR BPRAPAR = new BPRAPAR();
    BPRORGR BPRORGR = new BPRORGR();
    BPCFTLCM BPCFTLCM = new BPCFTLCM();
    SCCGWA SCCGWA;
    BPCSLIBA BPCSLIBA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCOWA SCCOWA;
    public void MP(SCCGWA SCCGWA, BPCSLIBA BPCSLIBA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSLIBA = BPCSLIBA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSLIBA return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSLIBA);
        WS_TBL_FLAG = ' ';
        B010_STARTBR_PROCESS();
        if (pgmRtn) return;
        WS_TBL_FLAG = 'Y';
        B020_READNEXT_PROCESS();
        if (pgmRtn) return;
        WS_TEMP_VARIABLE.WS_CNT = 0;
        CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_CNT);
        CEP.TRC(SCCGWA, "WS-TBL-FLAG1");
        CEP.TRC(SCCGWA, WS_TBL_FLAG);
        while (WS_TBL_FLAG != 'N') {
            CEP.TRC(SCCGWA, "WS-TBL-FLAG2");
            CEP.TRC(SCCGWA, WS_TBL_FLAG);
            WS_TEMP_VARIABLE.WS_CNT = WS_TEMP_VARIABLE.WS_CNT + 1;
            if (WS_TEMP_VARIABLE.WS_CNT == 1) {
                B040_01_OUT_TITLE();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, BPRALIB.APP_BR);
            if (BPRALIB.APP_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                WS_FOUND1_FLG = 'N';
                WS_FOUND2_FLG = 'N';
                IBS.init(SCCGWA, BPRORGR);
                BPRORGR.KEY.TYP = "07";
                BPRORGR.KEY.BR = BPRALIB.APP_BR;
                BPRORGR.REL_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                CEP.TRC(SCCGWA, BPRORGR.KEY.BR);
                CEP.TRC(SCCGWA, BPRORGR.REL_BR);
                BPTORGR_RD = new DBParm();
                BPTORGR_RD.TableName = "BPTORGR";
                BPTORGR_RD.where = "TYP = :BPRORGR.KEY.TYP "
                    + "AND BR = :BPRORGR.KEY.BR "
                    + "AND REL_BR = :BPRORGR.REL_BR";
                IBS.READ(SCCGWA, BPRORGR, this, BPTORGR_RD);
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
                if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                    WS_FOUND1_FLG = 'Y';
                } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                }
                IBS.init(SCCGWA, BPRORGR);
                BPRORGR.KEY.TYP = "07";
                BPRORGR.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPRORGR.REL_BR = BPRALIB.APP_BR;
                BPTORGR_RD = new DBParm();
                BPTORGR_RD.TableName = "BPTORGR";
                BPTORGR_RD.where = "TYP = :BPRORGR.KEY.TYP "
                    + "AND BR = :BPRORGR.KEY.BR "
                    + "AND REL_BR = :BPRORGR.REL_BR";
                IBS.READ(SCCGWA, BPRORGR, this, BPTORGR_RD);
                if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                    WS_FOUND2_FLG = 'Y';
                } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                }
                if (WS_FOUND1_FLG == 'Y' 
                    || WS_FOUND2_FLG == 'Y') {
                    B040_02_OUT_BRW_DATA();
                    if (pgmRtn) return;
                }
            } else {
                WS_FOUND1_FLG = 'N';
                WS_FOUND2_FLG = 'N';
                IBS.init(SCCGWA, BPRORGR);
                BPRORGR.KEY.TYP = "07";
                BPRORGR.KEY.BR = BPRALIB.UP_BR;
                BPRORGR.REL_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                CEP.TRC(SCCGWA, BPRORGR.KEY.BR);
                CEP.TRC(SCCGWA, BPRORGR.REL_BR);
                BPTORGR_RD = new DBParm();
                BPTORGR_RD.TableName = "BPTORGR";
                BPTORGR_RD.where = "TYP = :BPRORGR.KEY.TYP "
                    + "AND BR = :BPRORGR.KEY.BR "
                    + "AND REL_BR = :BPRORGR.REL_BR";
                IBS.READ(SCCGWA, BPRORGR, this, BPTORGR_RD);
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
                if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                    WS_FOUND1_FLG = 'Y';
                } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                }
                IBS.init(SCCGWA, BPRORGR);
                BPRORGR.KEY.TYP = "07";
                BPRORGR.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPRORGR.REL_BR = BPRALIB.UP_BR;
                BPTORGR_RD = new DBParm();
                BPTORGR_RD.TableName = "BPTORGR";
                BPTORGR_RD.where = "TYP = :BPRORGR.KEY.TYP "
                    + "AND BR = :BPRORGR.KEY.BR "
                    + "AND REL_BR = :BPRORGR.REL_BR";
                IBS.READ(SCCGWA, BPRORGR, this, BPTORGR_RD);
                if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                    WS_FOUND2_FLG = 'Y';
                } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                }
                if (WS_FOUND1_FLG == 'Y' 
                    || WS_FOUND2_FLG == 'Y') {
                    B040_02_OUT_BRW_DATA();
                    if (pgmRtn) return;
                }
                CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_CNT);
            }
            B020_READNEXT_PROCESS();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_CNT);
        B030_ENDBR_PROCESS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPRGST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_INQ_ORG_STATION, BPCPRGST);
        CEP.TRC(SCCGWA, BPCPRGST.RC.RC_CODE);
        CEP.TRC(SCCGWA, BPCPRGST.FLAG);
        CEP.TRC(SCCGWA, BPCPRGST.LVL_RELATION);
        if (BPCPRGST.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRGST.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void B010_STARTBR_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRALIB);
        IBS.init(SCCGWA, BPCRALIB);
        CEP.TRC(SCCGWA, "DEV");
        CEP.TRC(SCCGWA, BPCSLIBA.APP_NO);
        BPRALIB.KEY.APP_NO = BPCSLIBA.APP_NO;
        BPRALIB.APP_BR = BPCSLIBA.APP_BR;
        BPRALIB.UP_BR = BPCSLIBA.UP_BR;
        BPCRALIB.INFO.BEG_DT = BPCSLIBA.BEG_DT;
        BPCRALIB.INFO.END_DT = BPCSLIBA.END_DT;
        if (BPCSLIBA.APP_TYPE != ' ') {
            BPRALIB.APP_TYPE = BPCSLIBA.APP_TYPE;
        } else {
            BPRALIB.APP_TYPE = ALL.charAt(0);
        }
        if (BPCSLIBA.APP_STS != ' ') {
            BPRALIB.APP_STS = BPCSLIBA.APP_STS;
        } else {
            BPRALIB.APP_STS = ALL.charAt(0);
        }
        if (BPCSLIBA.ALLOT_TP != ' ') {
            BPRALIB.ALLOT_TYPE = BPCSLIBA.ALLOT_TP;
        } else {
            BPRALIB.ALLOT_TYPE = ALL.charAt(0);
        }
        BPCRALIB.INFO.FUNC = 'B';
        BPCRALIB.INFO.POINTER = BPRALIB;
        BPCRALIB.INFO.LEN = 1211;
        S000_CALL_BPZRALIB();
        if (pgmRtn) return;
    }
    public void B020_READNEXT_PROCESS() throws IOException,SQLException,Exception {
        BPCRALIB.INFO.FUNC = 'N';
        BPCRALIB.INFO.POINTER = BPRALIB;
        BPCRALIB.INFO.LEN = 1211;
        S000_CALL_BPZRALIB();
        if (pgmRtn) return;
        if (BPCRALIB.RETURN_INFO == 'F') {
            WS_TBL_FLAG = 'Y';
        } else if (BPCRALIB.RETURN_INFO == 'N') {
            WS_TBL_FLAG = 'N';
        } else {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR);
        }
    }
    public void S000_CALL_BPZFTLCM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-TLR-QRY-BR-CHK", BPCFTLCM);
        CEP.TRC(SCCGWA, BPCFTLCM.RC.RC_CODE);
        if (BPCFTLCM.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCFTLCM.RC.RC_CODE);
        }
    }
    public void B030_ENDBR_PROCESS() throws IOException,SQLException,Exception {
        BPCRALIB.INFO.FUNC = 'E';
        BPCRALIB.INFO.LEN = 1211;
        BPCRALIB.INFO.POINTER = BPRALIB;
        S000_CALL_BPZRALIB();
        if (pgmRtn) return;
        if (BPCRALIB.RETURN_INFO != 'F') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR);
        }
    }
    public void B040_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 908;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B040_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOALAO);
        BPCOALAO.APP_NO = BPRALIB.KEY.APP_NO;
        BPCOALAO.APP_BR = BPRALIB.APP_BR;
        BPCOALAO.UP_BR = BPRALIB.UP_BR;
        BPCOALAO.UP_TLR = BPRALIB.UP_TLR;
        BPCOALAO.ALLOT_TYPE = BPRALIB.ALLOT_TYPE;
        BPCOALAO.APP_TYPE = BPRALIB.APP_TYPE;
        BPCOALAO.APP_STS = BPRALIB.APP_STS;
        BPCOALAO.CONF_NO = BPRALIB.CONF_NO;
        BPCOALAO.MOV_DT = BPRALIB.MOV_DT;
        BPCOALAO.APP_TLR = BPRALIB.APP_TLR;
        BPCOALAO.APP_DT = BPRALIB.APP_DT;
        BPCOALAO.ACP_TLR = BPRALIB.ACP_TLR;
        BPCOALAO.ACP_DT = BPRALIB.ACP_DT;
        BPCOALAO.CONF_TLR = BPRALIB.CONF_TLR;
        BPCOALAO.CONF_DT = BPRALIB.CONF_DT;
        BPCOALAO.ADT_TLR = BPRALIB.ADT_TLR;
        BPCOALAO.ADT_DT = BPRALIB.ADT_DT;
        BPCOALAO.REJ_TLR = BPRALIB.REJ_TLR;
        BPCOALAO.REJ_DT = BPRALIB.REJ_DT;
        BPCOALAO.UNDO_TLR = BPRALIB.UNDO_TLR;
        BPCOALAO.UNDO_DT = BPRALIB.UNDO_DT;
        BPCOALAO.BACK_TLR = BPRALIB.BACK_TLR;
        BPCOALAO.BACK_DT = BPRALIB.BACK_DT;
        BPCOALAO.IN_TLR = BPRALIB.IN_TLR;
        BPCOALAO.IN_DT = BPRALIB.IN_DT;
        BPCOALAO.OUT_TLR = BPRALIB.OUT_TLR;
        BPCOALAO.OUT_DT = BPRALIB.OUT_DT;
        BPCOALAO.UPD_DT = BPRALIB.UPD_DT;
        BPCOALAO.UPD_TLR = BPRALIB.UPD_TLR;
        BPCOALAO.APP_NOTE = BPRALIB.APP_NOTE;
        CEP.TRC(SCCGWA, BPCOALAO.APP_NOTE);
        IBS.init(SCCGWA, BPRAPAR);
        BPRAPAR.KEY.APP_NO = BPRALIB.KEY.APP_NO;
        CEP.TRC(SCCGWA, BPRAPAR.KEY.APP_NO);
        T000_READ_BPTAPAR();
        if (pgmRtn) return;
        BPCOALAO.APP_CCY = BPRAPAR.APP_CCY;
        BPCOALAO.APP_AMT = BPRAPAR.APP_AMT;
        BPCOALAO.OUT_AMT = BPRAPAR.OUT_AMT;
        BPCOALAO.PVAL_INFO[1-1].PVAL = BPRAPAR.PVAL1;
        BPCOALAO.PVAL_INFO[1-1].NUM = BPRAPAR.NUM1;
        BPCOALAO.PVAL_INFO[2-1].PVAL = BPRAPAR.PVAL2;
        BPCOALAO.PVAL_INFO[2-1].NUM = BPRAPAR.NUM2;
        BPCOALAO.PVAL_INFO[3-1].PVAL = BPRAPAR.PVAL3;
        BPCOALAO.PVAL_INFO[3-1].NUM = BPRAPAR.NUM3;
        BPCOALAO.PVAL_INFO[4-1].PVAL = BPRAPAR.PVAL4;
        BPCOALAO.PVAL_INFO[4-1].NUM = BPRAPAR.NUM4;
        BPCOALAO.PVAL_INFO[5-1].PVAL = BPRAPAR.PVAL5;
        BPCOALAO.PVAL_INFO[5-1].NUM = BPRAPAR.NUM5;
        BPCOALAO.PVAL_INFO[6-1].PVAL = BPRAPAR.PVAL6;
        BPCOALAO.PVAL_INFO[6-1].NUM = BPRAPAR.NUM6;
        BPCOALAO.PVAL_INFO[7-1].PVAL = BPRAPAR.PVAL7;
        BPCOALAO.PVAL_INFO[7-1].NUM = BPRAPAR.NUM7;
        BPCOALAO.PVAL_INFO[8-1].PVAL = BPRAPAR.PVAL8;
        BPCOALAO.PVAL_INFO[8-1].NUM = BPRAPAR.NUM8;
        BPCOALAO.PVAL_INFO[9-1].PVAL = BPRAPAR.PVAL9;
        BPCOALAO.PVAL_INFO[9-1].NUM = BPRAPAR.NUM9;
        BPCOALAO.PVAL_INFO[10-1].PVAL = BPRAPAR.PVAL10;
        BPCOALAO.PVAL_INFO[10-1].NUM = BPRAPAR.NUM10;
        BPCOALAO.PVAL_INFO[11-1].PVAL = BPRAPAR.PVAL11;
        BPCOALAO.PVAL_INFO[11-1].NUM = BPRAPAR.NUM11;
        BPCOALAO.PVAL_INFO[12-1].PVAL = BPRAPAR.PVAL12;
        BPCOALAO.PVAL_INFO[12-1].NUM = BPRAPAR.NUM12;
        BPCOALAO.PVAL_INFO[13-1].PVAL = BPRAPAR.PVAL13;
        BPCOALAO.PVAL_INFO[13-1].NUM = BPRAPAR.NUM13;
        BPCOALAO.PVAL_INFO[14-1].PVAL = BPRAPAR.PVAL14;
        BPCOALAO.PVAL_INFO[14-1].NUM = BPRAPAR.NUM14;
        BPCOALAO.PVAL_INFO[15-1].PVAL = BPRAPAR.PVAL15;
        BPCOALAO.PVAL_INFO[15-1].NUM = BPRAPAR.NUM15;
        BPCOALAO.PVAL_INFO[16-1].PVAL = BPRAPAR.PVAL16;
        BPCOALAO.PVAL_INFO[16-1].NUM = BPRAPAR.NUM16;
        BPCOALAO.PVAL_INFO[17-1].PVAL = BPRAPAR.PVAL17;
        BPCOALAO.PVAL_INFO[17-1].NUM = BPRAPAR.NUM17;
        BPCOALAO.PVAL_INFO[18-1].PVAL = BPRAPAR.PVAL18;
        BPCOALAO.PVAL_INFO[18-1].NUM = BPRAPAR.NUM18;
        BPCOALAO.PVAL_INFO[19-1].PVAL = BPRAPAR.PVAL19;
        BPCOALAO.PVAL_INFO[19-1].NUM = BPRAPAR.NUM19;
        BPCOALAO.PVAL_INFO[20-1].PVAL = BPRAPAR.PVAL20;
        BPCOALAO.PVAL_INFO[20-1].NUM = BPRAPAR.NUM20;
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = BPRALIB.APP_BR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        BPCOALAO.APP_NM = BPCPQORG.CHN_NM;
