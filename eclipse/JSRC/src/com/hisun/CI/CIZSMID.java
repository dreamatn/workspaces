package com.hisun.CI;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZSMID {
    int JIBS_tmp_int;
    DBParm CITBAS_RD;
    brParm CITID_BR = new brParm();
    brParm CITALT_BR = new brParm();
    DBParm CITALT_RD;
    DBParm CITID_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_MAX_ROW = 10;
    int K_MAX_COL = 0;
    int K_COL_CNT = 0;
    int WS_EXP_DT = 0;
    String WS_ID_NO = " ";
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CIRBAS CIRBAS = new CIRBAS();
    CIRID CIRID = new CIRID();
    CIRID CIRIDN = new CIRID();
    CIRID CIRIDO = new CIRID();
    CIRALT CIRALT = new CIRALT();
    CICOID CICOID = new CICOID();
    CICCGHIS CICCGHIS = new CICCGHIS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSMID CICSMID;
    public void MP(SCCGWA SCCGWA, CICSMID CICSMID) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSMID = CICSMID;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZSMID return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSMID.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICSMID.FUNC);
        if (CICSMID.FUNC == 'B') {
            B020_BRW_ID_INF();
            if (pgmRtn) return;
        } else if (CICSMID.FUNC == 'A') {
            B030_ADD_ID_INF();
            if (pgmRtn) return;
        } else if (CICSMID.FUNC == 'M') {
            B040_MOD_ID_INF();
            if (pgmRtn) return;
        } else if (CICSMID.FUNC == 'D') {
            B050_DEL_ID_INF();
            if (pgmRtn) return;
        } else if (CICSMID.FUNC == 'I') {
            B060_INQ_ID_INF();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_FUNC_ERROR);
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSMID.DATA.CI_NO);
        if (CICSMID.DATA.CI_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "CI NO MUST INPUT");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICSMID.DATA.CI_NO;
        T000_READ_CITBAS();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND);
        }
        CEP.TRC(SCCGWA, CIRBAS.CI_TYP);
        if (CIRBAS.CI_TYP == '3' 
            && (CICSMID.FUNC == 'A' 
            || CICSMID.FUNC == 'M' 
            || CICSMID.FUNC == 'D')) {
            CEP.TRC(SCCGWA, CIRBAS.OPN_BR);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.HQT_BANK);
            if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != CIRBAS.OPN_BR 
                && SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != SCCGWA.COMM_AREA.HQT_BANK) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_FIN_CUST_CANT_MOD);
            }
        }
    }
    public void B020_BRW_ID_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRID);
        CIRID.KEY.CI_NO = CICSMID.DATA.CI_NO;
        T000_STARTBR_CITID();
        if (pgmRtn) return;
        T000_READNEXT_CITID();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "ID INF NOT FND");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_ID_INF_NOTFND);
        }
        R000_01_OUT_TITLE();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            IBS.init(SCCGWA, CICOID);
            CICOID.DATA.ID_TYPE = CIRID.KEY.ID_TYPE;
            CICOID.DATA.ID_NO = CIRID.ID_NO;
            CICOID.DATA.ID_REMARK = CIRID.REMARK;
            CICOID.DATA.ID_RGN = CIRID.ID_RGN;
            CICOID.DATA.EFF_DT = CIRID.EFF_DT;
            CICOID.DATA.EXP_DT = CIRID.EXP_DT;
            CICOID.DATA.OPEN_FLG = CIRID.OPEN;
            R000_02_OUT_BRW_DATA();
            if (pgmRtn) return;
            T000_READNEXT_CITID();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITID();
        if (pgmRtn) return;
    }
    public void B030_ADD_ID_INF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSMID.DATA.ID_NO);
        if (CICSMID.DATA.ID_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, "证件号码未输�?");
        }
        if (CICSMID.DATA.ID_TYPE.equalsIgnoreCase("23000")) {
            IBS.init(SCCGWA, CIRID);
            if (CICSMID.DATA.ID_NO == null) CICSMID.DATA.ID_NO = "";
            JIBS_tmp_int = CICSMID.DATA.ID_NO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) CICSMID.DATA.ID_NO += " ";
            if (CICSMID.DATA.ID_NO == null) CICSMID.DATA.ID_NO = "";
            JIBS_tmp_int = CICSMID.DATA.ID_NO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) CICSMID.DATA.ID_NO += " ";
            CIRID.ID_NO = CICSMID.DATA.ID_NO + "-" + CICSMID.DATA.ID_NO;
            if (CICSMID.DATA.ID_NO == null) CICSMID.DATA.ID_NO = "";
            JIBS_tmp_int = CICSMID.DATA.ID_NO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) CICSMID.DATA.ID_NO += " ";
            CEP.TRC(SCCGWA, CICSMID.DATA.ID_NO.substring(9 - 1, 9 + 9 - 1));
            CEP.TRC(SCCGWA, CIRID.ID_NO);
            T000_READ_CITID_BY_IDNO();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_ID_INF_EXIST);
            }
        }
        IBS.init(SCCGWA, CIRID);
        IBS.init(SCCGWA, CIRIDN);
        IBS.init(SCCGWA, CIRIDO);
        CIRID.KEY.CI_NO = CICSMID.DATA.CI_NO;
        CIRID.KEY.ID_TYPE = CICSMID.DATA.ID_TYPE;
        T000_READ_CITID();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ID_INF_EXIST, CICSMID.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, CIRID.KEY.CI_NO);
        CEP.TRC(SCCGWA, CIRID.KEY.ID_TYPE);
        CIRID.ID_NO = CICSMID.DATA.ID_NO;
        CIRID.REMARK = CICSMID.DATA.ID_REMARK;
        CIRID.ID_RGN = CICSMID.DATA.ID_RGN;
        CIRID.EFF_DT = CICSMID.DATA.EFF_DT;
        CIRID.EXP_DT = CICSMID.DATA.EXP_DT;
        CIRID.OPEN = 'N';
        CIRID.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRID.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRID.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRID.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRID.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CIRID.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_WRITE_CITID();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRID, CIRIDN);
        IBS.init(SCCGWA, CICCGHIS);
        CICCGHIS.DATA.TABLE_NM = "CITID";
        CICCGHIS.DATA.OLD_DATA_LEN = 374;
        CICCGHIS.DATA.NEW_DATA_LEN = 374;
        CICCGHIS.DATA.OLD_DATA_PTR = CIRIDO;
        CICCGHIS.DATA.NEW_DATA_PTR = CIRIDN;
        S000_CALL_CIZCGHIS();
        if (pgmRtn) return;
    }
    public void B040_MOD_ID_INF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSMID.DATA.ID_NO);
        if (CICSMID.DATA.ID_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, "证件号码未输�?");
        }
        if (!CICSMID.DATA.ID_TYPE.equalsIgnoreCase(CIRID.KEY.ID_TYPE) 
            || !CICSMID.DATA.ID_NO.equalsIgnoreCase(CIRID.ID_NO)) {
            WS_ID_NO = " ";
            if (CICSMID.DATA.ID_NO == null) CICSMID.DATA.ID_NO = "";
            JIBS_tmp_int = CICSMID.DATA.ID_NO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) CICSMID.DATA.ID_NO += " ";
            if (CICSMID.DATA.ID_NO == null) CICSMID.DATA.ID_NO = "";
            JIBS_tmp_int = CICSMID.DATA.ID_NO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) CICSMID.DATA.ID_NO += " ";
            WS_ID_NO = CICSMID.DATA.ID_NO + "-" + CICSMID.DATA.ID_NO;
            if (CIRID.KEY.ID_TYPE.equalsIgnoreCase("20001") 
                && CICSMID.DATA.ID_TYPE.equalsIgnoreCase("23000") 
                && CIRID.ID_NO.equalsIgnoreCase(WS_ID_NO)) {
            } else {
                if (CICSMID.DATA.ID_TYPE.equalsIgnoreCase("23000")) {
                    IBS.init(SCCGWA, CIRID);
                    CIRID.ID_NO = WS_ID_NO;
                    if (CICSMID.DATA.ID_NO == null) CICSMID.DATA.ID_NO = "";
                    JIBS_tmp_int = CICSMID.DATA.ID_NO.length();
                    for (int i=0;i<70-JIBS_tmp_int;i++) CICSMID.DATA.ID_NO += " ";
                    CEP.TRC(SCCGWA, CICSMID.DATA.ID_NO.substring(9 - 1, 9 + 9 - 1));
                    CEP.TRC(SCCGWA, CIRID.ID_NO);
                    T000_READ_CITID_BY_IDNO();
                    if (pgmRtn) return;
                    if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                        CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_ID_INF_EXIST);
                    }
                }
            }
        }
        IBS.init(SCCGWA, CIRID);
        IBS.init(SCCGWA, CIRIDN);
        IBS.init(SCCGWA, CIRIDO);
        CIRID.KEY.CI_NO = CICSMID.DATA.CI_NO;
        CIRID.KEY.ID_TYPE = CICSMID.DATA.ID_TYPE;
        T000_READ_CITID_UPD();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRID, CIRIDO);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ID_INF_NOTFND, CICSMID.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_EXP_DT = CIRID.EXP_DT;
        CIRID.ID_NO = CICSMID.DATA.ID_NO;
        CIRID.REMARK = CICSMID.DATA.ID_REMARK;
        CIRID.ID_RGN = CICSMID.DATA.ID_RGN;
        CIRID.EFF_DT = CICSMID.DATA.EFF_DT;
        CIRID.EXP_DT = CICSMID.DATA.EXP_DT;
        CIRID.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRID.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRID.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_REWRITE_CITID();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRID, CIRIDN);
        IBS.init(SCCGWA, CICCGHIS);
        CICCGHIS.DATA.TABLE_NM = "CITID";
        CICCGHIS.DATA.OLD_DATA_LEN = 374;
        CICCGHIS.DATA.NEW_DATA_LEN = 374;
        CICCGHIS.DATA.OLD_DATA_PTR = CIRIDO;
        CICCGHIS.DATA.NEW_DATA_PTR = CIRIDN;
        S000_CALL_CIZCGHIS();
        if (pgmRtn) return;
        if (CIRID.OPEN == 'Y' 
            && CIRID.EXP_DT > SCCGWA.COMM_AREA.AC_DATE) {
            CEP.TRC(SCCGWA, "CHECK EXP DATE");
            IBS.init(SCCGWA, CIRBAS);
            CIRBAS.KEY.CI_NO = CICSMID.DATA.CI_NO;
            T000_READ_CITBAS_UPD();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CIRID.EXP_DT);
            CEP.TRC(SCCGWA, CIRBAS.STSW);
            if (CIRBAS.STSW == null) CIRBAS.STSW = "";
            JIBS_tmp_int = CIRBAS.STSW.length();
            for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
            if (CIRBAS.STSW.substring(22 - 1, 22 + 1 - 1).equalsIgnoreCase("1")) {
                if (CIRBAS.STSW == null) CIRBAS.STSW = "";
                JIBS_tmp_int = CIRBAS.STSW.length();
                for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
                CIRBAS.STSW = CIRBAS.STSW.substring(0, 22 - 1) + "0" + CIRBAS.STSW.substring(22 + 1 - 1);
            }
            IBS.init(SCCGWA, CIRALT);
            CIRALT.ENTY_TYP = '0';
            CIRALT.ENTY_NO = CICSMID.DATA.CI_NO;
            CIRALT.ALT_TYP = "0018";
            T000_STARTBR_CITALT();
            if (pgmRtn) return;
            T000_READNEXT_CITALT();
            if (pgmRtn) return;
            while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
                CEP.TRC(SCCGWA, "DEL ALT 0018");
                T000_DELETE_CITALT();
                if (pgmRtn) return;
                T000_READNEXT_CITALT();
                if (pgmRtn) return;
            }
            T000_ENDBR_CITALT();
            if (pgmRtn) return;
            T000_REWRITE_CITBAS();
            if (pgmRtn) return;
        }
    }
    public void B050_DEL_ID_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRID);
        IBS.init(SCCGWA, CIRIDN);
        IBS.init(SCCGWA, CIRIDO);
        CIRID.KEY.CI_NO = CICSMID.DATA.CI_NO;
        CIRID.KEY.ID_TYPE = CICSMID.DATA.ID_TYPE;
        T000_READ_CITID_UPD();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRID, CIRIDO);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ID_INF_NOTFND, CICSMID.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        T000_DELETE_CITID();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CICCGHIS);
        CICCGHIS.DATA.TABLE_NM = "CITID";
        CICCGHIS.DATA.OLD_DATA_LEN = 374;
        CICCGHIS.DATA.NEW_DATA_LEN = 374;
        CICCGHIS.DATA.OLD_DATA_PTR = CIRIDO;
        CICCGHIS.DATA.NEW_DATA_PTR = CIRIDN;
        S000_CALL_CIZCGHIS();
        if (pgmRtn) return;
    }
    public void B060_INQ_ID_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRID);
        CIRID.KEY.CI_NO = CICSMID.DATA.CI_NO;
        CIRID.KEY.ID_TYPE = CICSMID.DATA.ID_TYPE;
        T000_READ_CITID();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CICSMID.RLT_FLG = 'Y';
            CICSMID.DATA.ID_NO = CIRID.ID_NO;
            CICSMID.DATA.ID_REMARK = CIRID.REMARK;
            CICSMID.DATA.ID_RGN = CIRID.ID_RGN;
            CICSMID.DATA.EFF_DT = CIRID.EFF_DT;
            CICSMID.DATA.EXP_DT = CIRID.EXP_DT;
            CICSMID.DATA.OPEN_FLG = CIRID.OPEN;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "ID INQUIRE NOT FIND");
            CICSMID.RLT_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITID";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void R000_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, CICOID);
        SCCMPAG.DATA_LEN = 220;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_CIZCGHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MOD-HISTORY", CICCGHIS);
        if (CICCGHIS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICCGHIS.RC);
        }
    }
    public void T000_READ_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void T000_READ_CITBAS_UPD() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        CITBAS_RD.upd = true;
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void T000_REWRITE_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.REWRITE(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void T000_STARTBR_CITID() throws IOException,SQLException,Exception {
        CITID_BR.rp = new DBParm();
        CITID_BR.rp.TableName = "CITID";
        CITID_BR.rp.eqWhere = "CI_NO";
        IBS.STARTBR(SCCGWA, CIRID, CITID_BR);
    }
    public void T000_READNEXT_CITID() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRID, this, CITID_BR);
    }
    public void T000_ENDBR_CITID() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITID_BR);
    }
    public void T000_STARTBR_CITALT() throws IOException,SQLException,Exception {
        CITALT_BR.rp = new DBParm();
        CITALT_BR.rp.TableName = "CITALT";
        CITALT_BR.rp.eqWhere = "ENTY_TYP , ENTY_NO , ALT_TYP";
        CITALT_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, CIRALT, CITALT_BR);
    }
    public void T000_READNEXT_CITALT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRALT, this, CITALT_BR);
    }
    public void T000_ENDBR_CITALT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITALT_BR);
    }
    public void T000_DELETE_CITALT() throws IOException,SQLException,Exception {
        CITALT_RD = new DBParm();
        CITALT_RD.TableName = "CITALT";
        IBS.DELETE(SCCGWA, CIRALT, CITALT_RD);
    }
    public void T000_READ_CITID() throws IOException,SQLException,Exception {
        CITID_RD = new DBParm();
        CITID_RD.TableName = "CITID";
        IBS.READ(SCCGWA, CIRID, CITID_RD);
    }
    public void T000_READ_CITID_UPD() throws IOException,SQLException,Exception {
        CITID_RD = new DBParm();
        CITID_RD.TableName = "CITID";
        CITID_RD.upd = true;
        IBS.READ(SCCGWA, CIRID, CITID_RD);
    }
    public void T000_WRITE_CITID() throws IOException,SQLException,Exception {
        CITID_RD = new DBParm();
        CITID_RD.TableName = "CITID";
        CITID_RD.errhdl = true;
        IBS.WRITE(SCCGWA, CIRID, CITID_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, "证件类型录入重复");
        }
    }
    public void T000_REWRITE_CITID() throws IOException,SQLException,Exception {
        CITID_RD = new DBParm();
        CITID_RD.TableName = "CITID";
        IBS.REWRITE(SCCGWA, CIRID, CITID_RD);
    }
    public void T000_DELETE_CITID() throws IOException,SQLException,Exception {
        CITID_RD = new DBParm();
        CITID_RD.TableName = "CITID";
        IBS.DELETE(SCCGWA, CIRID, CITID_RD);
    }
    public void T000_READ_CITID_BY_IDNO() throws IOException,SQLException,Exception {
        CITID_RD = new DBParm();
        CITID_RD.TableName = "CITID";
        CITID_RD.where = "ID_TYPE = '20001' "
            + "AND ID_NO = :CIRID.ID_NO";
        IBS.READ(SCCGWA, CIRID, this, CITID_RD);
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
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
