package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPRMM {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    DBParm BPTPARP_RD;
    DBParm BPTPARM_RD;
    brParm BPTPARM_BR = new brParm();
    boolean pgmRtn = false;
    int WS_I = 0;
    int WS_DATE = 0;
    BPZPRMM_WS_PARM WS_PARM = new BPZPRMM_WS_PARM();
    char WS_CURR_EFF_FLG = ' ';
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    char WS_DEL_FLG = ' ';
    char WS_DUP_FLG = ' ';
    char WS_EFF_TYP = ' ';
    BPCRCKPM BPCRCKPM = new BPCRCKPM();
    BPRPARM BPRPARM = new BPRPARM();
    BPRPARP BPRPARP = new BPRPARP();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCGWA SCCGWA;
    BPCPRMM BPCPRMM;
    BPRPRMT BPRPRMT = new BPRPRMT();
    SCCCWA SCCCWA;
    String LK_MMT = " ";
    public void MP(SCCGWA SCCGWA, BPCPRMM BPCPRMM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPRMM = BPCPRMM;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPRMM return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        BPCPRMM.RC.RC_APP = "BP";
        BPCPRMM.RC.RC_RTNCODE = 0;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.DAT_PTR);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPRMT);
        CEP.TRC(SCCGWA, BPRPRMT.KEY.BK);
        if (BPRPRMT.KEY.BK.trim().length() == 0) {
            BPRPRMT.KEY.BK = SCCGWA.COMM_AREA.TR_BANK;
        }
        CEP.TRC(SCCGWA, BPRPRMT.KEY.BK);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B01_CHECK();
        if (pgmRtn) return;
        if (BPCPRMM.FUNC == '0') {
            B02_FUNC_WRITE();
            if (pgmRtn) return;
        } else if (BPCPRMM.FUNC == '1') {
            B03_FUNC_DELETE();
            if (pgmRtn) return;
        } else if (BPCPRMM.FUNC == '2') {
            B04_FUNC_REWRITE();
            if (pgmRtn) return;
        } else if (BPCPRMM.FUNC == '3') {
            B05_FUNC_READ();
            if (pgmRtn) return;
        } else if (BPCPRMM.FUNC == '4') {
            B06_FUNC_READ_UPDATE();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCPRMM.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B01_CHECK() throws IOException,SQLException,Exception {
        if ((BPCPRMM.FUNC != '1') 
            && BPCPRMM.EFF_DT < SCCGWA.COMM_AREA.AC_DATE) {
            BPCPRMM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
        BPRPARP.KEY.TYPE = BPRPRMT.KEY.TYP;
        T00_READ_BPTPARP();
        if (pgmRtn) return;
        if (WS_FOUND_FLG == 'Y') {
            WS_EFF_TYP = BPRPARP.ENA;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_TYPE_NOTFND, BPCPRMM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_DEL_FLG = BPRPARP.DEL_FLG;
        WS_DUP_FLG = BPRPARP.DUP_DATE_FLG;
        if (WS_EFF_TYP == '0' 
            && BPCPRMM.FUNC == '0' 
            && BPCPRMM.EFF_DT > SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_INV_EFF_DT, BPCPRMM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if ((BPCPRMM.FUNC == '0' 
            || BPCPRMM.FUNC == '2') 
            && WS_DUP_FLG == 'N') {
            BPCPRMM.EFF_DT = 20000101;
            BPCPRMM.EXP_DT = 20991231;
        }
        if ((BPCPRMM.FUNC == '0' 
            || BPCPRMM.FUNC == '2') 
            && BPCPRMM.EXP_DT <= SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_INV_EXP_DT, BPCPRMM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B02_FUNC_WRITE() throws IOException,SQLException,Exception {
        WS_CURR_EFF_FLG = ' ';
        WS_UPDATE_FLG = ' ';
        if (BPCPRMM.EFF_DT == SCCGWA.COMM_AREA.AC_DATE) {
            WS_CURR_EFF_FLG = 'Y';
        }
        R00_READ();
        if (pgmRtn) return;
        if (WS_FOUND_FLG == 'Y') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_ALEADY_EXIST, BPCPRMM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPRPARM);
        BPRPARM.KEY.TYPE = BPRPRMT.KEY.TYP;
        BPRPARM.KEY.CODE = BPRPRMT.KEY.CD;
        BPRPARM.KEY.IBS_AC_BK = BPRPRMT.KEY.BK;
        BPRPARM.EFF_DATE = BPCPRMM.EFF_DT;
        BPRPARM.EXP_DATE = BPCPRMM.EXP_DT;
        BPRPARM.DESC = BPRPRMT.DESC;
        BPRPARM.CDESC = BPRPRMT.CDESC;
        BPRPARM.BLOB_VAL = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
        BPRPARM.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRPARM.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRPARM.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        T00_WRITE_BPTPARM();
        if (pgmRtn) return;
    }
    public void B03_FUNC_DELETE() throws IOException,SQLException,Exception {
        BPRPARM.KEY.TYPE = BPRPRMT.KEY.TYP;
        BPRPARM.KEY.CODE = BPRPRMT.KEY.CD;
        BPRPARM.KEY.IBS_AC_BK = BPRPRMT.KEY.BK;
        BPRPARM.EFF_DATE = BPCPRMM.EFF_DT;
        T00_READUPDATE_BPTPARM();
        if (pgmRtn) return;
        if (WS_FOUND_FLG == 'Y') {
            if (BPRPARP.CHK_CPNT.trim().length() > 0) {
                IBS.init(SCCGWA, BPCRCKPM);
                BPCRCKPM.FUNC = 'D';
                BPCRCKPM.TYPE = BPRPARM.KEY.TYPE;
                BPCRCKPM.CODE = BPRPARM.KEY.CODE;
                BPRPARM.KEY.IBS_AC_BK = BPRPRMT.KEY.BK;
                BPCRCKPM.VAL = BPRPARM.BLOB_VAL;
                S00_CALL_BPZRCKPM();
                if (pgmRtn) return;
            }
            BPRPRMT.DESC = BPRPARM.DESC;
            BPRPRMT.CDESC = BPRPARM.CDESC;
            IBS.CPY2CLS(SCCGWA, BPRPARM.BLOB_VAL, BPRPRMT.DAT_TXT);
            T00_DELETE_BPTPARM();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_NOTFND, BPCPRMM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B04_FUNC_REWRITE() throws IOException,SQLException,Exception {
        WS_DATE = BPRPARM.EFF_DATE;
        if (BPRPARM.EFF_DATE <= SCCGWA.COMM_AREA.AC_DATE 
            && BPCPRMM.EFF_DT > SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_INV_EFF_DT, BPCPRMM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPRPARM.EFF_DATE > SCCGWA.COMM_AREA.AC_DATE 
            && BPCPRMM.EFF_DT <= SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_INV_EFF_DT, BPCPRMM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPRPARM.EFF_DATE > SCCGWA.COMM_AREA.AC_DATE 
            && BPRPARM.EFF_DATE != BPCPRMM.EFF_DT) {
            T00_DELETE_BPTPARM();
            if (pgmRtn) return;
            IBS.init(SCCGWA, BPRPARM);
            BPRPARM.EFF_DATE = WS_DATE;
            BPRPARM.KEY.TYPE = BPRPRMT.KEY.TYP;
            BPRPARM.KEY.CODE = BPRPRMT.KEY.CD;
            BPRPARM.KEY.IBS_AC_BK = BPRPRMT.KEY.BK;
            BPRPARM.EFF_DATE = BPCPRMM.EFF_DT;
            BPRPARM.EXP_DATE = BPCPRMM.EXP_DT;
            BPRPARM.DESC = BPRPRMT.DESC;
            BPRPARM.CDESC = BPRPRMT.CDESC;
            BPRPARM.BLOB_VAL = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
            BPRPARM.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPRPARM.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPRPARM.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            T00_WRITE_BPTPARM();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, BPRPARM);
            BPRPARM.EFF_DATE = WS_DATE;
            BPRPARM.EXP_DATE = BPCPRMM.EXP_DT;
            BPRPARM.DESC = BPRPRMT.DESC;
            BPRPARM.CDESC = BPRPRMT.CDESC;
            BPRPARM.BLOB_VAL = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
            BPRPARM.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPRPARM.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPRPARM.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            T00_REWRITE_BPTPARM();
            if (pgmRtn) return;
        }
    }
    public void B05_FUNC_READ() throws IOException,SQLException,Exception {
        WS_CURR_EFF_FLG = ' ';
        WS_UPDATE_FLG = ' ';
        if (BPCPRMM.EFF_DT == SCCGWA.COMM_AREA.AC_DATE) {
            WS_CURR_EFF_FLG = 'Y';
        }
        R00_READ();
        if (pgmRtn) return;
        if (WS_FOUND_FLG != 'Y') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_NOTFND, BPCPRMM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        BPRPRMT.DESC = BPRPARM.DESC;
        BPRPRMT.CDESC = BPRPARM.CDESC;
        IBS.CPY2CLS(SCCGWA, BPRPARM.BLOB_VAL, BPRPRMT.DAT_TXT);
        BPCPRMM.TS = BPRPARM.TS;
        BPCPRMM.EXP_DT = BPRPARM.EXP_DATE;
        BPCPRMM.EFF_DT = BPRPARM.EFF_DATE;
    }
    public void B06_FUNC_READ_UPDATE() throws IOException,SQLException,Exception {
        WS_CURR_EFF_FLG = ' ';
        WS_UPDATE_FLG = 'Y';
        if (BPCPRMM.EFF_DT == SCCGWA.COMM_AREA.AC_DATE) {
            WS_CURR_EFF_FLG = 'Y';
        }
        R00_READ();
        if (pgmRtn) return;
        if (WS_FOUND_FLG != 'Y') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_NOTFND, BPCPRMM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        BPRPRMT.DESC = BPRPARM.DESC;
        BPRPRMT.CDESC = BPRPARM.CDESC;
        IBS.CPY2CLS(SCCGWA, BPRPARM.BLOB_VAL, BPRPRMT.DAT_TXT);
        BPCPRMM.TS = BPRPARM.TS;
        BPCPRMM.EXP_DT = BPRPARM.EXP_DATE;
        BPCPRMM.EFF_DT = BPRPARM.EFF_DATE;
    }
    public void R00_READ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPARM);
        BPRPARM.KEY.TYPE = BPRPRMT.KEY.TYP;
        BPRPARM.KEY.CODE = BPRPRMT.KEY.CD;
        BPRPARM.KEY.IBS_AC_BK = BPRPRMT.KEY.BK;
        BPRPARM.EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.PARM_IND);
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        if (SCCGWA.COMM_AREA.PARM_IND == 'E') {
            WS_I = 9596;
            R000_SET_LK_MMT();
            if (pgmRtn) return;
            WS_PARM = (BPZPRMM_WS_PARM) IBS.HASHLKUP(SCCGWA, BPRPARM.KEY.TYPE, BPRPRMT.KEY);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                WS_FOUND_FLG = 'Y';
                WS_I = WS_I - ( 9596 - 9500 );
                if (WS_PARM.WS_VAL_TEXT == null) WS_PARM.WS_VAL_TEXT = "";
                JIBS_tmp_int = WS_PARM.WS_VAL_TEXT.length();
                for (int i=0;i<9500-JIBS_tmp_int;i++) WS_PARM.WS_VAL_TEXT += " ";
                if (BPRPARM.BLOB_VAL == null) BPRPARM.BLOB_VAL = "";
                JIBS_tmp_int = BPRPARM.BLOB_VAL.length();
                for (int i=0;i<9500-JIBS_tmp_int;i++) BPRPARM.BLOB_VAL += " ";
                BPRPARM.BLOB_VAL = WS_PARM.WS_VAL_TEXT.substring(0, WS_I) + BPRPARM.BLOB_VAL.substring(WS_I);
                BPRPARM.EFF_DATE = WS_PARM.WS_EFF_DATE;
                BPRPARM.EXP_DATE = WS_PARM.WS_EXP_DATE;
                BPRPARM.DESC = WS_PARM.WS_DESC;
                BPRPARM.CDESC = WS_PARM.WS_CDESC;
            } else {
                if (BPCPRMM.FUNC == '0' 
                    && WS_DUP_FLG == 'N') {
                    T00_READ_BPTPARM_3();
                    if (pgmRtn) return;
                } else {
                    if (WS_CURR_EFF_FLG == 'Y') {
                        T00_READ_BPTPARM_1();
                        if (pgmRtn) return;
                    } else {
                        T00_READ_BPTPARM_2();
                        if (pgmRtn) return;
                    }
                }
            }
        } else {
            if (BPCPRMM.FUNC == '0' 
                && WS_DUP_FLG == 'N') {
                T00_READ_BPTPARM_3();
                if (pgmRtn) return;
            } else {
                if (WS_CURR_EFF_FLG == 'Y') {
                    T00_READ_BPTPARM_1();
                    if (pgmRtn) return;
                } else {
                    T00_READ_BPTPARM_2();
                    if (pgmRtn) return;
                }
            }
        }
    } else { //FROM #ELSE
        if (BPCPRMM.FUNC == '0' 
            && WS_DUP_FLG == 'N') {
            T00_READ_BPTPARM_3();
            if (pgmRtn) return;
        } else {
            if (WS_CURR_EFF_FLG == 'Y') {
                T00_READ_BPTPARM_1();
                if (pgmRtn) return;
            } else {
                T00_READ_BPTPARM_2();
                if (pgmRtn) return;
            }
        }
    } //FROM #ENDIF
        if (WS_FOUND_FLG == 'Y' 
            && WS_UPDATE_FLG == 'Y') {
            T00_READUPDATE_BPTPARM();
            if (pgmRtn) return;
        }
    }
    public void S00_CALL_BPZRCKPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, BPRPARP.CHK_CPNT, BPCRCKPM);
        if (BPCRCKPM.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRCKPM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPRMM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T00_READ_BPTPARP() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        if (SCCGWA.COMM_AREA.PARM_IND == 'E') {
            SCCCWA = (SCCCWA) SCCGWA.COMM_AREA.CWA_PTR;
            if (SCCCWA.PARM_IN_USE[7-1] == 1) {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR1_AREA[7-1].PARM_PTR1);
            } else {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR2_AREA[7-1].PARM_PTR2);
            }
            BPRPARP = (BPRPARP) IBS.HASHLKUP(SCCGWA, BPRPARP.KEY.TYPE, BPRPARP.KEY.TYPE);
            if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
                BPTPARP_RD = new DBParm();
                BPTPARP_RD.TableName = "BPTPARP";
                IBS.READ(SCCGWA, BPRPARP, BPTPARP_RD);
            }
        } else {
            BPTPARP_RD = new DBParm();
            BPTPARP_RD.TableName = "BPTPARP";
            IBS.READ(SCCGWA, BPRPARP, BPTPARP_RD);
        }
    } else { //FROM #ELSE
        BPTPARP_RD = new DBParm();
        BPTPARP_RD.TableName = "BPTPARP";
        IBS.READ(SCCGWA, BPRPARP, BPTPARP_RD);
    } //FROM #ENDIF
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_FLG = 'Y';
        } else {
            WS_FOUND_FLG = ' ';
        }
    }
    public void T00_WRITE_BPTPARM() throws IOException,SQLException,Exception {
        BPTPARM_RD = new DBParm();
        BPTPARM_RD.TableName = "BPTPARM";
        IBS.WRITE(SCCGWA, BPRPARM, BPTPARM_RD);
        if (SCCGWA.COMM_AREA.PARM_IND == 'E') {
            GET_PARM_CHANGED();
            if (pgmRtn) return;
        }
    }
    public void T00_DELETE_BPTPARM() throws IOException,SQLException,Exception {
        BPTPARM_RD = new DBParm();
        BPTPARM_RD.TableName = "BPTPARM";
        IBS.DELETE(SCCGWA, BPRPARM, BPTPARM_RD);
        if (SCCGWA.COMM_AREA.PARM_IND == 'E') {
            GET_PARM_CHANGED();
            if (pgmRtn) return;
        }
    }
    public void T00_READ_BPTPARM_1() throws IOException,SQLException,Exception {
        WS_FOUND_FLG = ' ';
        BPTPARM_RD = new DBParm();
        BPTPARM_RD.TableName = "BPTPARM";
        BPTPARM_RD.eqWhere = "TYPE, CODE,IBS_AC_BK";
        BPTPARM_RD.where = "EFF_DATE <= :BPRPARM.EFF_DATE";
        BPTPARM_RD.fst = true;
        BPTPARM_RD.order = "EFF_DATE DESC";
        IBS.READ(SCCGWA, BPRPARM, this, BPTPARM_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_FLG = 'Y';
        }
    }
    public void T00_READ_BPTPARM_2() throws IOException,SQLException,Exception {
        WS_FOUND_FLG = ' ';
        BPTPARM_RD = new DBParm();
        BPTPARM_RD.TableName = "BPTPARM";
        BPTPARM_RD.eqWhere = "TYPE, CODE,IBS_AC_BK";
        BPTPARM_RD.where = "EFF_DATE > :BPRPARM.EFF_DATE";
        BPTPARM_RD.fst = true;
        BPTPARM_RD.order = "EFF_DATE DESC";
        IBS.READ(SCCGWA, BPRPARM, this, BPTPARM_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_FLG = 'Y';
        }
    }
    public void T00_READ_BPTPARM_3() throws IOException,SQLException,Exception {
        WS_FOUND_FLG = ' ';
        BPTPARM_RD = new DBParm();
        BPTPARM_RD.TableName = "BPTPARM";
        BPTPARM_RD.eqWhere = "TYPE, CODE,IBS_AC_BK";
        BPTPARM_RD.where = "EXP_DATE >= :BPRPARM.EFF_DATE";
        BPTPARM_RD.fst = true;
        BPTPARM_RD.order = "EFF_DATE DESC";
        IBS.READ(SCCGWA, BPRPARM, this, BPTPARM_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_FLG = 'Y';
        }
    }
    public void T00_STARTBR_BPTPARM_1() throws IOException,SQLException,Exception {
        BPTPARM_BR.rp = new DBParm();
        BPTPARM_BR.rp.TableName = "BPTPARM";
        BPTPARM_BR.rp.eqWhere = "TYPE,CODE,IBS_AC_BK";
        BPTPARM_BR.rp.where = "EFF_DATE <= :BPRPARM.EFF_DATE "
            + "AND EXP_DATE > :BPRPARM.EFF_DATE";
        BPTPARM_BR.rp.order = "EFF_DATE DESC";
        IBS.STARTBR(SCCGWA, BPRPARM, this, BPTPARM_BR);
    }
    public void T00_STARTBR_BPTPARM_2() throws IOException,SQLException,Exception {
        BPTPARM_BR.rp = new DBParm();
        BPTPARM_BR.rp.TableName = "BPTPARM";
        BPTPARM_BR.rp.eqWhere = "TYPE,CODE,IBS_AC_BK";
        BPTPARM_BR.rp.where = "EFF_DATE > :BPRPARM.EFF_DATE";
        IBS.STARTBR(SCCGWA, BPRPARM, this, BPTPARM_BR);
    }
    public void T00_STARTBR_BPTPARM_3() throws IOException,SQLException,Exception {
        BPTPARM_BR.rp = new DBParm();
        BPTPARM_BR.rp.TableName = "BPTPARM";
        BPTPARM_BR.rp.eqWhere = "TYPE,CODE,IBS_AC_BK";
        BPTPARM_BR.rp.where = "EXP_DATE >= :BPRPARM.EFF_DATE";
        IBS.STARTBR(SCCGWA, BPRPARM, this, BPTPARM_BR);
    }
    public void T00_READNEXT_BPTPARM() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRPARM, this, BPTPARM_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_FLG = 'Y';
        }
    }
    public void T00_READUPDATE_BPTPARM() throws IOException,SQLException,Exception {
        BPTPARM_RD = new DBParm();
        BPTPARM_RD.TableName = "BPTPARM";
        BPTPARM_RD.upd = true;
        IBS.READ(SCCGWA, BPRPARM, BPTPARM_RD);
        WS_FOUND_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_FLG = 'Y';
        }
    }
    public void T00_REWRITE_BPTPARM() throws IOException,SQLException,Exception {
        BPTPARM_RD = new DBParm();
        BPTPARM_RD.TableName = "BPTPARM";
        IBS.REWRITE(SCCGWA, BPRPARM, BPTPARM_RD);
        if (SCCGWA.COMM_AREA.PARM_IND == 'E') {
            GET_PARM_CHANGED();
            if (pgmRtn) return;
        }
    }
    public void T00_ENDBR_BPTPARM() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTPARM_BR);
    }
    public void GET_PARM_CHANGED() throws IOException,SQLException,Exception {
        if (BPRPRMT.KEY.TYP.equalsIgnoreCase("B")) {
            SCCGWA.COMM_AREA.PARM_CHANGED = 1;
        } else if (BPRPRMT.KEY.TYP.equalsIgnoreCase("CPN")) {
            SCCGWA.COMM_AREA.PARM_CHANGED = 2;
        } else if (BPRPRMT.KEY.TYP.equalsIgnoreCase("TIRUL")) {
            SCCGWA.COMM_AREA.PARM_CHANGED = 2;
        } else if (BPRPRMT.KEY.TYP.equalsIgnoreCase("D")) {
            SCCGWA.COMM_AREA.PARM_CHANGED = 3;
        } else if (BPRPRMT.KEY.TYP.equalsIgnoreCase("I")) {
            SCCGWA.COMM_AREA.PARM_CHANGED = 4;
        } else if (BPRPRMT.KEY.TYP.equalsIgnoreCase("MSG")) {
            SCCGWA.COMM_AREA.PARM_CHANGED = 5;
        } else if (BPRPRMT.KEY.TYP.equalsIgnoreCase("MSGCK")) {
            SCCGWA.COMM_AREA.PARM_CHANGED = 6;
        } else if (BPRPRMT.KEY.TYP.equalsIgnoreCase("O")) {
            SCCGWA.COMM_AREA.PARM_CHANGED = 7;
        } else if (BPRPRMT.KEY.TYP.equalsIgnoreCase("PARMC")) {
            SCCGWA.COMM_AREA.PARM_CHANGED = 8;
        } else if (BPRPRMT.KEY.TYP.equalsIgnoreCase("PARMT")) {
            SCCGWA.COMM_AREA.PARM_CHANGED = 8;
        } else if (BPRPRMT.KEY.TYP.equalsIgnoreCase("TRT")) {
            SCCGWA.COMM_AREA.PARM_CHANGED = 9;
        } else if (BPRPRMT.KEY.TYP.equalsIgnoreCase("AMTL")) {
            SCCGWA.COMM_AREA.PARM_CHANGED = 12;
        } else if (BPRPRMT.KEY.TYP.equalsIgnoreCase("ORGM")) {
            SCCGWA.COMM_AREA.PARM_CHANGED = 12;
        } else {
            SCCGWA.COMM_AREA.PARM_CHANGED = 10;
        }
    }
    public void R000_SET_LK_MMT() throws IOException,SQLException,Exception {
        SCCCWA = (SCCCWA) SCCGWA.COMM_AREA.CWA_PTR;
        if (BPRPRMT.KEY.TYP.equalsIgnoreCase("B")) {
            if (SCCCWA.PARM_IN_USE[1-1] == 1) {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR1_AREA[1-1].PARM_PTR1);
            } else {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR2_AREA[1-1].PARM_PTR2);
            }
        } else if (BPRPRMT.KEY.TYP.equalsIgnoreCase("CPN")) {
            if (SCCCWA.PARM_IN_USE[2-1] == 1) {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR1_AREA[2-1].PARM_PTR1);
            } else {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR2_AREA[2-1].PARM_PTR2);
            }
        } else if (BPRPRMT.KEY.TYP.equalsIgnoreCase("TIRUL")) {
            if (SCCCWA.PARM_IN_USE[2-1] == 1) {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR1_AREA[2-1].PARM_PTR1);
            } else {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR2_AREA[2-1].PARM_PTR2);
            }
        } else if (BPRPRMT.KEY.TYP.equalsIgnoreCase("D")) {
            if (SCCCWA.PARM_IN_USE[3-1] == 1) {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR1_AREA[3-1].PARM_PTR1);
            } else {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR2_AREA[3-1].PARM_PTR2);
            }
        } else if (BPRPRMT.KEY.TYP.equalsIgnoreCase("I")) {
            if (SCCCWA.PARM_IN_USE[4-1] == 1) {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR1_AREA[4-1].PARM_PTR1);
            } else {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR2_AREA[4-1].PARM_PTR2);
            }
        } else if (BPRPRMT.KEY.TYP.equalsIgnoreCase("MSG")) {
            if (SCCCWA.PARM_IN_USE[5-1] == 1) {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR1_AREA[5-1].PARM_PTR1);
            } else {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR2_AREA[5-1].PARM_PTR2);
            }
        } else if (BPRPRMT.KEY.TYP.equalsIgnoreCase("MSGCK")) {
            if (SCCCWA.PARM_IN_USE[6-1] == 1) {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR1_AREA[6-1].PARM_PTR1);
            } else {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR2_AREA[6-1].PARM_PTR2);
            }
        } else if (BPRPRMT.KEY.TYP.equalsIgnoreCase("O")) {
            if (SCCCWA.PARM_IN_USE[7-1] == 1) {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR1_AREA[7-1].PARM_PTR1);
            } else {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR2_AREA[7-1].PARM_PTR2);
            }
        } else if (BPRPRMT.KEY.TYP.equalsIgnoreCase("PARMC")) {
            if (SCCCWA.PARM_IN_USE[8-1] == 1) {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR1_AREA[8-1].PARM_PTR1);
            } else {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR2_AREA[8-1].PARM_PTR2);
            }
        } else if (BPRPRMT.KEY.TYP.equalsIgnoreCase("PARMT")) {
            if (SCCCWA.PARM_IN_USE[8-1] == 1) {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR1_AREA[8-1].PARM_PTR1);
            } else {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR2_AREA[8-1].PARM_PTR2);
            }
        } else if (BPRPRMT.KEY.TYP.equalsIgnoreCase("TRT")) {
            if (SCCCWA.PARM_IN_USE[9-1] == 1) {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR1_AREA[9-1].PARM_PTR1);
            } else {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR2_AREA[9-1].PARM_PTR2);
            }
        } else if (BPRPRMT.KEY.TYP.equalsIgnoreCase("AMTL")) {
            if (SCCCWA.PARM_IN_USE[12-1] == 1) {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR1_AREA[12-1].PARM_PTR1);
            } else {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR2_AREA[12-1].PARM_PTR2);
            }
        } else if (BPRPRMT.KEY.TYP.equalsIgnoreCase("ORGM")) {
            if (SCCCWA.PARM_IN_USE[12-1] == 1) {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR1_AREA[12-1].PARM_PTR1);
            } else {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR2_AREA[12-1].PARM_PTR2);
            }
        } else {
            if (SCCCWA.PARM_IN_USE[10-1] == 1) {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR1_AREA[10-1].PARM_PTR1);
            } else {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR2_AREA[10-1].PARM_PTR2);
            }
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void JIBS_RETURN() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPRMM.DAT_PTR);
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
